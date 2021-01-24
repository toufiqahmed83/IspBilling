package com.isp.billing.controller;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.service.ChkNetwrk;
import com.isp.billing.service.ClientInfoService;
import com.isp.billing.service.InterfaceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Toufiq on 7/29/2019.
 */
@Controller
@RequestMapping(value = "/ACRC")
public class AutoCompleteReqController {

    @Autowired
    private ClientInfoService clientInfoService;

    @Autowired
    private InterfaceHeaderService interfaceHeaderService;


    @RequestMapping(value = "/getClient/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<ClientInfo> getClientByName(@PathVariable("name") String name)
    {
        return this.clientInfoService.findByName(name);
    }

    @RequestMapping(value = "/getInterfaceActivity/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String getInterfaceActivity(@PathVariable("id") Long id)
    {
        System.out.println("xxx");
        ChkNetwrk cn = new ChkNetwrk();
        InterfaceHeader ih = this.interfaceHeaderService.findById(id);
//        if (cn.isInterfaceOnline(ih.getIpAddress()));
        if (cn.isInterfaceOnline(ih.getIpAddress()))
        {
            return "true";
        }else
        {
            return "false";
        }
//        return cn.isInterfaceOnline(ih.getIpAddress());
    }
}
