package com.isp.billing.controller;

import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.Search;
import com.isp.billing.service.ClientBillInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Toufiq on 7/29/2019.
 */
@Controller
@RequestMapping(value = "/ClientBill")
public class ClientBillController {

    @Autowired
    private ClientBillInfoService clientBillInfoService;


    @GetMapping(value = "/getAll/{page}")
    public String getClientBill(@PathVariable("page") Integer page, Model model) {

        this.setList(model,page,new Search());

        return "clientBillInfo";
    }

    @GetMapping(value = "/updateBillStatus/{page}/{trnsId}")
    public String updateBillStatus(@PathVariable("page") Integer page,
            @PathVariable("trnsId") Long trnsId, Model model)
    {
        ClientBillInfo clientBillInfo= this.clientBillInfoService.findBayId(trnsId);
        if (clientBillInfo.getBillStatus())
        {
            model.addAttribute("msg","Cannot Change Bill Status");
//            return "clientBillInfo";
//            clientBillInfo.setBillStatus(false);
        }else
        {
            clientBillInfo.setBillStatus(true);
            this.clientBillInfoService.save(clientBillInfo);
        }

        setList(model,page,new Search());
        return "clientBillInfo";
    }


    @RequestMapping(value = "/Search")
    public String search(Search search,Model model)
    {
//        this.clientBillInfoService.Search(search,0);
        this.setList(model,0,search);

        return "clientBillInfo";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }


    private void setList(Model model,Integer page,Search search)
    {
        PageRequest pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        System.out.println(pageable.getPageNumber());
//        Page<ClientBillInfo> clientBillInfos = this.clientBillInfoService.findAll(pageable);
        Page<ClientBillInfo> clientBillInfos = this.clientBillInfoService.Search(search,pageable);
        int totalPages = clientBillInfos.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("clientBills", clientBillInfos);
        model.addAttribute("page", page);
        model.addAttribute("Search", new Search());
    }
}
