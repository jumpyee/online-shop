package com.stoliar.petproject.gadgetshop.controller;


import com.stoliar.petproject.gadgetshop.entity.BuyOrder;
import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.entity.OrderedItem;
import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.service.BuyOrderService;
import com.stoliar.petproject.gadgetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/async/order")
public class OrderAjaxController {

    @Autowired
    private BuyOrderService buyOrderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_order")
    @ResponseBody
    public boolean addOrder(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        Set<Item> itemsInUserCart = user.getCart().keySet();
        Set<OrderedItem> orderedItems = new HashSet<>();
        Double totalPrice = 0.0;
        for (Item item : itemsInUserCart) {
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setItemId(item.getId());
            orderedItem.setName(item.getName());
            orderedItem.setImageReference(item.getImageReference());
            orderedItem.setDescription(item.getDescription());
            orderedItem.setPrice(item.getPrice());
            orderedItem.setAmount(user.getCart().get(item));
            orderedItems.add(orderedItem);
            totalPrice += item.getPrice() * user.getCart().get(item);
        }
        buyOrderService.saveBuyOrder(new BuyOrder(totalPrice, new Date().toString(), orderedItems, user));
        user.getCart().clear();
        userService.updateUser(user);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/delete_order", method = RequestMethod.POST)
    public boolean deleteItem(@RequestParam("id") Integer id) {
        if (buyOrderService.findBuyOrderById(id) != null) {
            buyOrderService.deleteBuyOrder(buyOrderService.findBuyOrderById(id));
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/change_status", method = RequestMethod.POST)
    public boolean changeBuyOrder(@RequestParam("id") Integer id, @RequestParam("status") String status) {
        if (buyOrderService.findBuyOrderById(id) != null) {
            BuyOrder buyOrder = buyOrderService.findBuyOrderById(id);
            buyOrder.setStatus(status);
            buyOrderService.modifyBuyOrder(buyOrder);
            return true;
        }
        return false;
    }


}
