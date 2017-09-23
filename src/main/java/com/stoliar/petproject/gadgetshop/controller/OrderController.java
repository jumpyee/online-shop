package com.stoliar.petproject.gadgetshop.controller;

import com.stoliar.petproject.gadgetshop.entity.BuyOrder;
import com.stoliar.petproject.gadgetshop.service.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/order")
public class OrderController {

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
