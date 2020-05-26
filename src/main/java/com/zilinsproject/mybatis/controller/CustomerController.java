package com.zilinsproject.mybatis.controller;


import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.enums.UserEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.form.ProductForm;
import com.zilinsproject.mybatis.form.UserLoginForm;
import com.zilinsproject.mybatis.form.UserRegisterForm;
import com.zilinsproject.mybatis.service.UserService;
import com.zilinsproject.mybatis.service.imp.UserServiceImpl;
import com.zilinsproject.mybatis.utils.CustomerConst;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


/**
 * @author zilinsmac
 */
@Controller
@RequestMapping("/user")

public class CustomerController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("customer/index");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterForm form,
                                 BindingResult bindingResult,
                                 Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
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

    @PostMapping("/login")
    public String login(@Valid UserLoginForm form,
                        BindingResult bindingResult,
                        HttpSession session,
                        Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "/user/index");
            return "common/error";
        }

        try {
            UserInfo userInfo = userService.login(form.getUsername(), form.getPassword());
            //设置session
            session.setAttribute(CustomerConst.CURRENT_USER, userInfo);

        } catch (CustomizeException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/user/index");
            return "common/error";
        }
        return "redirect:/user/home";
    }

    @GetMapping("/home")
    public ModelAndView homepage(Map<String, Object> map, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(CustomerConst.CURRENT_USER);
        map.put("userInfo", userInfo);
        return new ModelAndView("customer/homepage");
    }

    @GetMapping("/logout")
    public ModelAndView logout(Map<String, Object> map, HttpSession session){
        session.removeAttribute(CustomerConst.CURRENT_USER);
        map.put("url", "/user/index");
        return new ModelAndView("common/success", map);
    }


}
