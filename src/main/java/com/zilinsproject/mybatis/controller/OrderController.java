package com.zilinsproject.mybatis.controller;


import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.imp.CartServiceImpl;
import com.zilinsproject.mybatis.service.imp.OrderServiceImpl;
import com.zilinsproject.mybatis.service.imp.ProductPageServiceImpl;
import com.zilinsproject.mybatis.utils.CustomerConst;
import com.zilinsproject.mybatis.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zilinsmac
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProductPageServiceImpl productService;
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/create")
    public ModelAndView getOrderItems(Map<String, Object> map,
                                      HttpSession session){
        //形成初始订单详情
        Integer user_id = (Integer) session.getAttribute(CustomerConst.CURRENT_USER);
        List<CartInfo> cartInfoList = cartService.getAllItemsFromCart(user_id);
        try {
            //创建订单
            OrderDTO orderDTO = orderService.createOrder(user_id, cartInfoList);
            //清空购物车
            cartService.deleteCartFromRedis(user_id);
            map.put("orderDTO", orderDTO);
            return new ModelAndView("order/summary", map);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/product/cart");
            return new ModelAndView("common/error", map);
        }

    }

    @GetMapping("/in_progress")
    public ModelAndView getOrderHistoryInProgress(@RequestParam("user_id") Integer user_id,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                        Map<String, Object> map){

        PageInfo<OrderDTO> pageInfo = orderService.listAllOrdersInProgress(user_id, pageNum, pageSize);
        map.put("user_id", user_id);
        map.put("pageInfo", pageInfo);
        return new ModelAndView("customer/orderHistory", map);
    }

    @GetMapping("/complete")
    public ModelAndView getOrderHistoryComplete(@RequestParam("user_id") Integer user_id,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                Map<String, Object> map){
        PageInfo<OrderDTO> pageInfo = orderService.listAllOrdersCompleted(user_id, pageNum, pageSize);
        map.put("user_id", user_id);
        map.put("pageInfo", pageInfo);
        return new ModelAndView("customer/orderHistoryComplete", map);
    }

    @GetMapping("/summary")
    public ModelAndView completeOrder(@RequestParam("order_id") String order_id,
                                      Map<String, Object> map){

        OrderDTO orderDTO = orderService.selectOrderById(order_id);
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/summary", map);
    }

    @GetMapping("/delete")
    public ModelAndView deleteOrder(@RequestParam("user_id") Integer user_id,
                                    @RequestParam("order_id") String order_id,
                                    Map<String, Object> map){
        try {
            orderService.deleteOrderById(order_id);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/order/in_progress?user_id=" + user_id.toString());
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/order/in_progress?user_id=" + user_id.toString());
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/close_order")
    public ModelAndView completeOrder(@RequestParam("user_id") Integer user_id,
                                      @RequestParam("order_id") String order_id,
                                      Map<String, Object> map){
        try {
            orderService.completeOrder(order_id);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/order/complete?user_id=" + user_id.toString());
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/order/complete?user_id=" + user_id.toString());
        return new ModelAndView("common/success", map);
    }
}
