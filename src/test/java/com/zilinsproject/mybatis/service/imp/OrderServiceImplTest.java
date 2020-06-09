package com.zilinsproject.mybatis.service.imp;

import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    @Transactional
    public void createOrder() {
        Integer user_id = 13;
        List<CartInfo> cartInfoList = new ArrayList<>();
        CartInfo item = new CartInfo();
        item.setProduct_id(9);
        item.setProduct_name("薯片");
        item.setCart_number(2);
        item.setProduct_price(new BigDecimal(5));
        cartInfoList.add(item);
        OrderDTO orderDTO = orderService.createOrder(user_id, cartInfoList);
        System.out.println(orderDTO.toString());
    }

    @Test
    public void testSelectAllInProgress(){
        PageInfo<OrderDTO> page = orderService.listAllOrdersInProgress(13, 1, 10);
        System.out.println(page.getList());
    }

    @Test
    @Transactional
    public void testDeleteOrder(){
        String order_id = "1591303738714557283";
        orderService.deleteOrderById(order_id);

    }

    @Test
    @Transactional
    public void testCompleteOrder(){
        String order_id = "1591303738714557283";
        orderService.completeOrder(order_id);
    }

    @Test
    public void testGetAllComplete(){
        PageInfo<OrderDTO> pageInfo = orderService.listAllOrdersCompleted(13, 1, 5);
        System.out.println(pageInfo.getList().size());
    }

    @Test
    public void testCalcPrice(){
        String order_id = "1591658890917245612";
        Integer category_id = 1;
        System.out.println(orderService.calcPriceOfProductsByCategoryId(order_id, category_id));
    }
}