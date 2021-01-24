package com.isp.billing.controller;

import com.isp.billing.model.sms.SmsConfg;
import com.isp.billing.service.SmsConfgService;
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
 * Created by Iqbal on 8/1/2019.
 */
@Controller
@RequestMapping("/Smsconfig")
public class SmsConfigCtl {

    @Autowired
    public SmsConfgService smsConfgService;

    //// new iqbal
    @GetMapping("/GetAll")
    public String findAll(Model model){
        model.addAttribute("smsConfg", this.smsConfgService.findAll());
        return "sms_config_index";
    }

    @GetMapping("/EditSms/{mode}/{id}")
    public String getOne(@PathVariable("mode") String mode,
                         @PathVariable("id") Long id,
                         Model model){
        model.addAttribute("mode", mode);
        model.addAttribute("smsConfg", this.smsConfgService.findById(id));
        return "sms_config_create";
    }

    @GetMapping("/smsConfCreate/{mode}")
    public String createNew(@PathVariable("mode") String mode,
                            Model model){
        SmsConfg smsConfg = new SmsConfg();
        model.addAttribute("mode", mode);
        model.addAttribute("smsConfg", smsConfg);

        return "sms_config_create";
    }
    @PostMapping("/smsConfCreate/{mode}")
    public String editSave(@PathVariable("mode") String mode,
                           @Valid SmsConfg smsConfg,
                           Model model){
        if(smsConfg!=null){
            this.smsConfgService.save(smsConfg , mode);
            model.addAttribute("smsConfg",this.smsConfgService.findAll());
            return "sms_config_index";
        }else {
            model.addAttribute("mode", mode);
            model.addAttribute("smsConfg", smsConfg);
            return "sms_config_create";
        }

    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }


}
