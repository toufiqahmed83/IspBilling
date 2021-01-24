package com.isp.billing.controller;

import com.isp.billing.model.sms.SmsLog;
import com.isp.billing.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iqbal on 7/30/2019.
 */
@Controller
@RequestMapping(value = "/smslog")
public class SmsLogCtl {
    @Autowired
    private SmsLogService smsLog;

    @GetMapping("/GetAll")
    public String ShowALl(Model model){
        model.addAttribute("AllSmsLog", this.smsLog.findAll());
        return "sms_log_index";
    }


}
