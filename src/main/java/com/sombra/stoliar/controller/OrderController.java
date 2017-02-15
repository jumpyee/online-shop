package com.sombra.stoliar.controller;

import com.sombra.stoliar.entity.BuyOrder;
import com.sombra.stoliar.service.BuyOrderService;
import com.sombra.stoliar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/orders")
public class OrderController {


    @Autowired
    private UserService userService;

    @Autowired
    private BuyOrderService buyOrderService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String ordersPage(Model model) {
        return "orders/orders";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String orderPage(@PathVariable(value = "id") Integer id, Model model) {
        BuyOrder buyOrder = buyOrderService.findBuyOrderById(id);
        if (buyOrder != null) {
            model.addAttribute("order", buyOrder);
        }
        return "order/order";
    }


}
