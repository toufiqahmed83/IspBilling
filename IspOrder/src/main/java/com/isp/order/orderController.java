package com.isp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Toufiq on 9/26/2019.
 */
@Controller
@RequestMapping(value = "/Order")
public class orderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/findAll")
    public String findAll(Model model)
    {
        model.addAttribute("allOrder",this.orderService.findAll());

        return "order";
    }
}
