package com.isp.billing.controller;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.service.DashBoard.DBInterFaceHeaderService;
import com.isp.billing.service.InterfaceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toufiq on 8/4/2019.
 */
@Controller
@RequestMapping(value = "/Dashboard")
public class DashboardController
{
    private List<InterfaceHeader> interfaceHeaders;
    @Autowired
    private DBInterFaceHeaderService dbInterFaceHeaderService;




    @GetMapping(value = "/InterFaceHeader")
    public String getInterfaceStatus(Model model)
    {
//        IReactiveDataDriverContextVariable iRddCv = new ReactiveDataDriverContextVariable(this.dbInterFaceHeaderService.findAll(), 1);

        model.addAttribute("intrDb",this.dbInterFaceHeaderService.findAll());
        return "/DashBoard/DBInterfaceHeader :: resultsList";
//        return "/DashBoard/index";
    }

    @GetMapping(value = "/ClientInfos")
    public String getClientInfos(Model model)
    {
//        IReactiveDataDriverContextVariable iRddCv = new ReactiveDataDriverContextVariable(this.dbInterFaceHeaderService.findAll(), 1);

        model.addAttribute("intrDb",this.dbInterFaceHeaderService.findAllClientInfos());
        return "/DashBoard/DBClientInfos :: resultsList";
//        return "/DashBoard/index";
    }
}
