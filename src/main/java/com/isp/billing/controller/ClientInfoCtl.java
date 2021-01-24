package com.isp.billing.controller;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.service.ClientInfoService;
import com.isp.billing.service.InterfaceLineService;
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
import java.util.List;

/**
 * Created by Iqbal on 7/25/2019.
 */
@Controller
@RequestMapping(value = "/client")
public class ClientInfoCtl {

    @Autowired
    private ClientInfoService clientInfoService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private InterfaceLineService interfaceLineService;


    @GetMapping("/GetAll")
    public String getAllClient(Model model) {
        model.addAttribute("AllClient", this.clientInfoService.findAll());

        return "client_info_index";
    }


    @GetMapping("/GetClient/{mode}/{id}")
    public String getOneClient(@PathVariable("id") Long id,
                               @PathVariable("mode") String mode,
                               Model model) {

        model.addAttribute("mode", mode);
        model.addAttribute("clientCrt", this.clientInfoService.findById(id));
        model.addAttribute("smsTemp", this.smsTemplateService.findAll());
        model.addAttribute("pppoes", this.interfaceLineService.findAll());


        return "client_create";
    }

    @GetMapping("/CreateClient/{mode}")
    public String CreateClient(@PathVariable("mode") String mode,
                               ClientInfo clientCrt,
                               Model model) {
        clientCrt = new ClientInfo();

        model.addAttribute("mode", mode);
        model.addAttribute("clientCrt", clientCrt);
        model.addAttribute("smsTemp", this.smsTemplateService.findAll());
        model.addAttribute("pppoes", this.interfaceLineService.findAll());
        return "client_create";
    }

    @PostMapping("/CreateClient/{mode}")
    public String SaveClient(@PathVariable("mode") String mode,
                             @Valid ClientInfo clientCrt,
                             Model model) {
        if (clientCrt != null) {
            clientCrt.setPpoe(this.interfaceLineService.findById(clientCrt.getInterfaceLine().getId()).getPpoe());
            this.clientInfoService.saveClient(clientCrt, mode);
            model.addAttribute("AllClient", this.clientInfoService.findAll());

            return "client_info_index";
        } else {
            model.addAttribute("mode", mode);
            model.addAttribute("clientCrt", clientCrt);

            return "client_create";
        }

    }

//    @GetMapping("/DelClient/{id}")
//    public String DeleteClient(@PathVariable("id") Long id,
//                               Model model) {
//        ClientInfo clientInfo = this.clientInfoService.findById(id);
//        if (clientInfo != null) {
//            this.clientInfoService.deletClient(clientInfo);
//
//        }
//        model.addAttribute("AllClient", this.clientInfoService.findAll());
//
//        return "client_info_index";
//
//
//    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    //iqbal modified
    @GetMapping("/ShowAll")
    public String shAllClnt(Model ui) {
        ui.addAttribute("AllClient", this.clientInfoService.findAll());

        return "client_info_index";
    }

}
