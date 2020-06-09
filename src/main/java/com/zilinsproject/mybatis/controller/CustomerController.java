package com.zilinsproject.mybatis.controller;

import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.entity.UserTransaction;
import com.zilinsproject.mybatis.enums.UserEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.form.*;
import com.zilinsproject.mybatis.service.imp.OrderServiceImpl;
import com.zilinsproject.mybatis.service.imp.SmsServiceImpl;
import com.zilinsproject.mybatis.service.imp.TransactionServiceImpl;
import com.zilinsproject.mybatis.service.imp.UserServiceImpl;
import com.zilinsproject.mybatis.utils.CustomerConst;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;


/**
 * @author zilinsmac
 */
@Controller
@RequestMapping("/user")

public class CustomerController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TransactionServiceImpl transactionService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private SmsServiceImpl smsService;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("customer/login");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterForm form,
                                 BindingResult bindingResult,
                                 Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setRole(UserEnum.CUSTOMER.getCode());
        BeanUtils.copyProperties(form, userInfo);

        try {
            userService.register(userInfo);
        } catch (CustomizeException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/user/index");
        return new ModelAndView("common/success");

    }

    @PostMapping("/getCode")
    public ModelAndView getCode(@Valid UserCodeForm form,
                                BindingResult bindingResult,
                                Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        try {
            smsService.SendSmsLoginCode(form.getUsername());
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        map.put("username", form.getUsername());
        return new ModelAndView("customer/loginCode", map);

    }



    @PostMapping("/login")
    public ModelAndView login(@Valid UserCodeLoginForm form,
                        BindingResult bindingResult,
                        HttpSession session,
                        Map<String, Object> map){

        if (bindingResult.hasErrors()){
            map.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        try {
            UserInfo userInfo = smsService.loginViaCode(form.getUsername(), form.getCode());
            //设置session
            session.setAttribute(CustomerConst.CURRENT_USER, userInfo.getUser_id());
            return homepage(session, map);

        } catch (CustomizeException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

    }

    @GetMapping("/home")
    public ModelAndView homepage(HttpSession session,
                                 Map<String, Object> map){
        Integer user_id = (Integer) session.getAttribute(CustomerConst.CURRENT_USER);
        System.out.println(user_id);
        UserInfo userInfo = userService.selectByUserId(user_id);
        map.put("userInfo", userInfo);
        return new ModelAndView("customer/homepage", map);
    }


    @GetMapping("/logout")
    public ModelAndView logout(Map<String, Object> map, HttpSession session){
        session.removeAttribute(CustomerConst.CURRENT_USER);
        map.put("url", "/user/index");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/add_balance")
    public ModelAndView addBalance(@PathParam("id") Integer id,
                                   Map<String, Object>map){

        try {
            userService.selectByUserId(id);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/user/index");
            return new ModelAndView("common/error", map);
        }

        UserTransaction transaction = new UserTransaction();
        transaction.setUser_id(id);
        map.put("transaction", transaction);
        return new ModelAndView("customer/add_balance", map);
    }


    @PostMapping("/submit_balance")
    public ModelAndView submitBalance(@Valid BalanceSubmitForm form,
                                      BindingResult bindingResult,
                                      Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            map.put("url", "/user/home");
            return new ModelAndView("common/error", map);
        }

        //copy transaction info
        UserTransaction transaction = new UserTransaction();
        BeanUtils.copyProperties(form, transaction);

        try{
            //update user balance
            UserInfo userInfo = userService.selectByUserId(form.getUser_id());
            userInfo.setUser_balance(userInfo.getUser_balance().add(form.getAmount()));
            userService.updateUserBalance(userInfo);
            //create transaction record
            transactionService.insert(transaction);
            map.put("userInfo", userInfo);

        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/user/home");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/user/home");
        return new ModelAndView("common/success", map);


    }

    @GetMapping("/transaction_history")
    public ModelAndView getTransactionHistory(@RequestParam("user_id") Integer user_id,
                                              Map<String, Object> map){
        try{
            userService.selectByUserId(user_id);
            List<UserTransaction> transactions = transactionService.getTransactionsByUserId(user_id);

            //sort transactions by date
            Collections.sort(transactions, new Comparator<UserTransaction>() {
                @Override
                public int compare(UserTransaction t1, UserTransaction t2) {
                    return t2.getCreate_time().compareTo(t1.getCreate_time());
                }
            });
            map.put("transactions", transactions);
            return new ModelAndView("customer/transactions", map);

        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/user/home");
            return new ModelAndView("common/error", map);
        }

    }


}
