package com.zilinsproject.mybatis.controller;

import com.zilinsproject.mybatis.entity.*;
import com.zilinsproject.mybatis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.swing.plaf.IconUIResource;
import java.util.*;


@Controller
@RequestMapping("customer")
public class CustomerPageController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;


    @RequestMapping("/{cid}")
    public String home(Model model, @PathVariable("cid") String cid){
        Customer customer = customerService.selectByPrimaryKey(cid);
        model.addAttribute("user", customer);
        return "home-customer";
    }

    @RequestMapping("/edit-balance/{cid}")
    public String editBalance(Model model, @PathVariable("cid") String cid){
        Transactions t = new Transactions();
        String tid = UUID.randomUUID().toString().toLowerCase();
        t.setCid(cid);
        t.setTid(tid);
        model.addAttribute("transaction", t);
        return "edit-balance";
    }

    @RequestMapping("/add-balance")
    public String addBalance(Transactions t, Model model){
        String cid = t.getCid();
        Date d = new Date();
        t.setCreated_at(d);
        transactionService.insert(t);
        Customer customer = customerService.selectByPrimaryKey(cid);
        float newBalance = customer.getBalance() + t.getAmount();
        customer.setBalance(newBalance);
        customerService.updateByPrimaryKey(customer);
        model.addAttribute("user", customer);
        return "home-customer";
    }

    @RequestMapping("/getAllTransaction/{cid}")
    public String getAllTrans(Model model, @PathVariable("cid") String cid){
        List<Transactions> tl = transactionService.selectAllTransactionsByCid(cid);
        Collections.sort(tl, new Comparator<Transactions> () {
            @Override
            public int compare(Transactions t1, Transactions t2) {
                return t2.getCreated_at().compareTo(t1.getCreated_at());
            }
        });
        model.addAttribute("transactions", tl);
        model.addAttribute("customerID", cid);
        return "list-transactions";
    }
}
