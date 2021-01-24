package com.isp.billing.service;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aye.mikrotiklib.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iqbal on 7/31/2019.
 */
@Service
public class MikrotikActivityServiceImpl {

@Autowired
private InterfaceHeaderService interfaceHeaderService;

@Autowired
private InterfaceLineService interfaceLineService;


    private  String uname;
    private String pass;
    private String host;
    private String activity;
    private InterfaceHeader interfaceHeader;
    private InterfaceLine interfaceLine;


    private PPPoeActivity mc;
    private NetworkChk nc;

    public  void changeStatus(Boolean a,Long id)
    {
        setStatus(a);
        interfaceLine=this.interfaceLineService.findById(id);
        setInterData(interfaceLine.getInterfaceHeader());
        mc = new PPPoeActivity();
        try {
            mc.connect(host,uname,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            mc.changePPPoeStatus(activity, interfaceLine.getPpoe());
        } catch (MikrotikApiException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPPPoeTrue(String n,Long id)
    {
        Boolean r= false;
        interfaceHeader = this.interfaceHeaderService.findById(id);
        setInterData(interfaceHeader);
        mc = new PPPoeActivity();
        try {
            mc.connect(host,uname,pass);
            r=mc.isPppoeTrue(n);
        } catch (Exception e) {
            r=false;
//            e.printStackTrace();
        }

        return r;
    }

public List<Map<String, String>> getAllPPPoe(Long id)
{
    List<Map<String, String>> results = null;
            interfaceHeader = this.interfaceHeaderService.findById(id);
    setInterData(interfaceHeader);
    mc = new PPPoeActivity();

    try {
        mc.connect(host,uname,pass);
        results = (List<Map<String, String>>) mc.getAllPPPoe();
    } catch (Exception e) {
        System.out.println(e.getMessage());
        Map<String, String> err= new HashMap<>();
        err.put("Error",e.getMessage());
        results.add(err);
        e.printStackTrace();

    }
    return results;
}

    public List<Map<String, String>> getPingRes(Long id,String ip,int count)
    {
        List<Map<String, String>> results = null;
        interfaceHeader = this.interfaceHeaderService.findById(id);
        setInterData(interfaceHeader);
        nc = new NetworkChk();
        try {
            nc.connect(host,uname,pass);
            results = (List<Map<String, String>>) nc.ping(ip,count);
        } catch (Exception e) {
            Map<String,String> m = new HashMap<>();
            System.out.println(e.getMessage());
            e.printStackTrace();
            m.put(null,e.getMessage());
            results.add(m);
        }
        return results;
    }



//===============================================================================================
    private void setInterData(InterfaceHeader interfaceHeader)
    {
//        interfaceHeader=this.interfaceHeaderService.findById(interData.getInterfaceHeader().getId());
        this.uname=interfaceHeader.getInterFaceUserName();
        this.pass=interfaceHeader.getInterFacePass();
        this.host=interfaceHeader.getIpAddress();

    }

    private void setStatus(Boolean a)
    {
        if (a==true)
        {
            activity =ActionMode.ACTIVE.toString();
        }else
        {
            activity =ActionMode.INACTIVE.toString();
        }
    }



}
