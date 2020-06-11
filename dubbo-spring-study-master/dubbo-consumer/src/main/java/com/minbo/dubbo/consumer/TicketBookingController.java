package com.minbo.dubbo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.minbo.dubbo.provider.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketBookingController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/")
    public String toHome(){
//        System.out.println("tohome");
        return "/tickets.html";
    }

    /**
     * @return purchase status
     */
    @ResponseBody
    @RequestMapping("/purchase")
    public String purchase() {
        JSONObject jsonObject = new JSONObject();
        boolean succ = demoService.confirmPurchase();
        return succ?"purchase successfully completed":"purchase fail";
    }

}
