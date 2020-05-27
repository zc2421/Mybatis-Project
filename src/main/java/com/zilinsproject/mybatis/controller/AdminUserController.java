package com.zilinsproject.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

/**
 * @author Zilin Chen
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

//    @Autowired
//    private CustomerService customerService;
//
//    @RequestMapping
//    public String getAllCustomers(Model model)
//    {
//        List<Customer> clist = customerService.selectAll();
//        model.addAttribute("customers", clist);
//        return "list-customers";
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String deleteUser(Model model, @PathVariable("id") String id){
//        customerService.deleteByPrimaryKey(id);
//        return "redirect:/admin";
//    }
//
//    @RequestMapping(path = {"/edit", "/edit/{id}"})
//    public String addUser(Model model, @PathVariable("id") Optional<String> id){
//        if (id.isPresent()){
//            Customer oldCustomer = customerService.selectByPrimaryKey(id.get());
//            model.addAttribute("customer",oldCustomer);
//        } else {
//            Customer newCustomer = new Customer();
//            String cid = UUID.randomUUID().toString().toLowerCase();
//            newCustomer.setCid(cid);
//            model.addAttribute("customer", newCustomer);
//        }
//        return "add-customer";
//
//    }
//
//    @RequestMapping(value = "createCustomer", method = RequestMethod.POST)
//    public String create(Customer customer) {
//        if (customerService.selectByPrimaryKey(customer.getCid()) != null){
//            customerService.updateByPrimaryKey(customer);
//        } else {
//            customerService.insert(customer);
//        }
//        return "redirect:/admin";
//    }

}
