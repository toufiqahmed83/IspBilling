package com.isp.billing.controller;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;
import com.isp.billing.model.networkConf.PingModel;
import com.isp.billing.model.networkConf.PingRes;
import com.isp.billing.service.InterfaceHeaderService;
import com.isp.billing.service.InterfaceLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Toufiq on 7/31/2019.
 */
@Controller
@RequestMapping(value = "/Interface")
public class InterfaceController {

    @Autowired
    private InterfaceHeaderService interfaceHeaderService;

    @Autowired
    private InterfaceLineService interfaceLineService;


    @GetMapping(value = "/interface/{mode}")
    public String getInterFace(@PathVariable("mode") String mode,
                               final InterfaceHeader interfaceHeader,
                               ModelMap model) {
        interfaceHeader.setInterFaecStatus(true);
        model.addAttribute("interfaceheader", interfaceHeader);
        model.addAttribute("mode", mode);
        return "networkInterface";
    }

    @RequestMapping(value = "/interface/{mode}", params = "add")
    public String addInterFace(@PathVariable("mode") String mode,
                               final InterfaceHeader interfaceHeader,
                               ModelMap model) {
        this.interfaceHeaderService.save(interfaceHeader, mode);
        model.addAttribute("mode", mode);
        model.addAttribute("interfaceheader", interfaceHeader);
        return "networkInterface";
    }


    @GetMapping(value = "/editInterface/{mode}/{id}")
    public String editInterFace(@PathVariable("mode") String mode, @PathVariable("id") Long id,
                                InterfaceHeader interfaceHeader,
                                ModelMap model) {
        interfaceHeader = this.interfaceHeaderService.findById(id);
        model.addAttribute("mode", mode);
        model.addAttribute("interfaceheader", interfaceHeader);
//        getInterfacePPPoE(interfaceHeader);
        return "networkInterface";
    }

    @RequestMapping(value = "/interface/{mode}", params = "getFromDevice")
    public String getInterFacePPPoe(@PathVariable("mode") String mode,
                               final InterfaceHeader interfaceHeader,
                               ModelMap model) {
//        this.interfaceHeaderService.save(interfaceHeader, mode);
        model.addAttribute("mode", mode);
        this.interfaceHeaderService.getInterfacePPPoE(interfaceHeader);
        model.addAttribute("interfaceheader", interfaceHeader);
        return "networkInterface";
    }



    @RequestMapping(value = "/interface/{mode}", params = "addRow")
    public String addInterFaceline(@PathVariable("mode") String mode,
                                   final InterfaceHeader interfaceHeader,
                                   ModelMap model) {
        InterfaceLine il = new InterfaceLine();
        il.setInterfaceHeader(interfaceHeader);
        il.setLineStatus(true);
        il.setStartDates(new Date());
        interfaceHeader.getInterfaceLines().add(il);
        model.addAttribute("mode", mode);
        model.addAttribute("interfaceheader", interfaceHeader);
        return "networkInterface";
    }

    @RequestMapping(value = "/interface/{mode}", params = "saveDetail")
    public String saveInterFaceline(@PathVariable("mode") String mode,
                                    final InterfaceHeader interfaceHeader,
                                    ModelMap model) {
        this.interfaceLineService.savelines(interfaceHeader, mode);
        model.addAttribute("mode", mode);
        model.addAttribute("interfaceheader", interfaceHeader);
        return "networkInterface";
    }

    @RequestMapping(value = "/interface/{mode}", params = "removeRow")
    public String deleteInterFaceline(@PathVariable("mode") String mode,
                                      final InterfaceHeader interfaceHeader,
                                      final HttpServletRequest req,
                                      ModelMap model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        if (interfaceHeader.getInterfaceLines().get(rowId.intValue()).getId() == null) {
            interfaceHeader.getInterfaceLines().remove(rowId.intValue());
        } else {

            this.interfaceLineService.delete(this.interfaceLineService.findById(interfaceHeader.getInterfaceLines().get(rowId.intValue()).getId()));
            interfaceHeader.getInterfaceLines().remove(rowId.intValue());
        }
        model.addAttribute("mode", mode);
        model.addAttribute("interfaceheader", interfaceHeader);
        return "networkInterface";
    }


    @GetMapping(value = "/search")
    public String search(Model model) {
        InterfaceHeader ih = new InterfaceHeader();
        model.addAttribute("interfaceheader", ih);
        return "networkInterfaceSearch";
    }


    @RequestMapping(value = "/search", params = "search")
    public String searchRes(@Valid InterfaceHeader interfaceHeader, Model model) {
        PageRequest pageable = PageRequest.of(0, 5);
        Page<InterfaceHeader> interfaceHeaders = this.interfaceHeaderService.search(interfaceHeader, pageable);
        int totalPages = interfaceHeaders.getTotalPages();
        model.addAttribute("interfaceHeaders", interfaceHeaders);

        model.addAttribute("interfaceheader", interfaceHeader);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        return "networkInterfaceSearch";
    }


    public void getInterfacePPPoE(InterfaceHeader interfaceHeader) {
        this.interfaceHeaderService.getInterfacePPPoE(interfaceHeader);
    }

//    @GetMapping(value = "/ping/{id}")
//    public String ping(@PathVariable("id") Long id,Model model)
//    {
//        InterfaceHeader i = this.interfaceHeaderService.findById(id);
//        PingRes pingRes =this.interfaceHeaderService.testPing(i,this.IP,1);
//        System.out.println(pingRes.getResults());
//
//            for(Map<String, String> y: pingRes.getResults())
//            {
//                System.out.println(y.entrySet());
////                System.out.println(y.keySet());
////                System.out.println(y.values());
//            }
//
//        model.addAttribute("interfaceheader", i);
//        return "networkInterface";
//    }

//==========================

    private List<String> pingResults;

    @GetMapping(value = "/getPing")
    private String getPing(PingModel pingModel, Model model) {
        model.addAttribute("pingModel", new PingModel());
        model.addAttribute("interfaces", this.interfaceHeaderService.findAll());
        return "NetworkPing";
    }

    @GetMapping(value = "/Ping/{interFaceId}/{ip}/{i}")
    public String ping(@PathVariable("interFaceId") Long interFaceId,
                       @PathVariable("ip") String ip,
                       @PathVariable("i") int a,
                       Model model) {

        if (a == 1) {
            this.pingResults = new ArrayList<>();
        }
        InterfaceHeader i = this.interfaceHeaderService.findById(interFaceId);
        PingRes pingRes = this.interfaceHeaderService.testPing(i, ip, 1);
        System.out.println(a + " " + new Date());
        System.out.println(this.pingResults.size() + " >>" + a);

        if (pingRes.getResults() != null) {


            for (Map<String, String> y : pingRes.getResults()) {
//                System.out.println(y + " <--");
                this.pingResults.add(y.entrySet().toString());
//                System.out.println(y.keySet());
//                System.out.println(y.values());
            }

        }

        model.addAttribute("pingResults", pingResults);
        model.addAttribute("msg", pingRes.getMsg());
        return "PingResult :: resultsList";
    }

    private String IP = "87.248.98.8";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }
}
