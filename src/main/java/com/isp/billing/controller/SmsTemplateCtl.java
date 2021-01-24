package com.isp.billing.controller;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.SmsTemplate;
import com.isp.billing.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Iqbal on 7/27/2019.
 */

@Controller
@RequestMapping(value = "/sms")
public class SmsTemplateCtl {

    @Autowired
    private SmsTemplateService smsService;

    @GetMapping("/GetAll")
    public String GetMsgBody(Model model) {
        model.addAttribute("AllSms", this.smsService.findAll());
        return "sms_info";
    }

//    @GetMapping("/CreateSmsTemplate/{id}")
//    public String CreateNewSmsTemp(@PathVariable("id") Long id,SmsTemplate smscart, Model ui){
//        smscart = new SmsTemplate();
//        ui.addAttribute("viesms", this.smsService.findById(id));
//       return "sms_templ_create";
//    }

    /**
     * Created by Iqbal on 7/29/2019.
     */

    @GetMapping("/EditSms/{mode}/{id}")
    public String getOneClient(@PathVariable("id") Long id,
                               @PathVariable("mode") String mode,
                               Model model) {

        model.addAttribute("mode", mode);
        model.addAttribute("smscart", this.smsService.findById(id));


        return "sms_templ_create";
    }


    @GetMapping("/CreateSmsTemplate/{mode}")
    public String CreateClient(@PathVariable("mode") String mode,
                               SmsTemplate smscart,
                               Model model) {
        smscart = new SmsTemplate();

        model.addAttribute("mode", mode);
        model.addAttribute("smscart", smscart);

        return "sms_templ_create";
    }

    @PostMapping("/CreateSmsTemplate/{mode}")
    public String EditSmsTemplate(@PathVariable("mode") String mode,
                                  @Valid SmsTemplate smscart,
                                  Model model) {

//        smscart.setStatus(true);
        this.smsService.saveSms(smscart, mode);
        model.addAttribute("AllSms", this.smsService.findAll());
        return "sms_info";
    }


    @GetMapping("/CreateSmsTemplate")
    public String CreateMsg(SmsTemplate smscart, Model model) {
        smscart = new SmsTemplate();
        model.addAttribute("smscart", smscart);
        return "sms_templ_create";
    }

    @PostMapping("/CreateSmsTemplate")
    public String SavigMsg(@Valid SmsTemplate smsTemplate, Model model) {
        this.smsService.saveSms(smsTemplate, "E");
        model.addAttribute("AllSms", this.smsService.findAll());
        return "sms_info";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}

