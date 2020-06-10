package com.minbo.dubbo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.minbo.dubbo.provider.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tickets")
public class TicketBookingController {
    @Autowired
    DemoService demoService;

    /**
     * @param name 名字
     * @return
     */
    @ResponseBody
    @RequestMapping("/purchase/{name}")
    public JSONObject purchase(@PathVariable("name") String name) {
        JSONObject jsonObject = new JSONObject();
        String Str = demoService.confirmPurchase(name);
        jsonObject.put("str", Str);
        return jsonObject;
    }

}
