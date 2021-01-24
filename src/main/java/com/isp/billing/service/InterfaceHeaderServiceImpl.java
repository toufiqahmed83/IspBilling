package com.isp.billing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;
import com.isp.billing.model.networkConf.PPPoEClass;
import com.isp.billing.model.networkConf.PingRes;
import com.isp.billing.repo.InterfaceHeaderRepo;
import com.isp.billing.repo.InterfaceLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Toufiq on 7/31/2019.
 */
@Service
public class InterfaceHeaderServiceImpl implements InterfaceHeaderService {

    @Autowired
    private InterfaceHeaderRepo interfaceHeaderRepo;

    @Autowired
    private InterfaceLineRepo interfaceLineRepo;

    @Autowired
    private CommonColumnServiceImpl commonColumnService;

    @Autowired
    private MikrotikActivityServiceImpl mikrotikActivityService;


    @Override
    public List<InterfaceHeader> findAll() {
        return this.interfaceHeaderRepo.findAll();
    }

    @Override
    public InterfaceHeader findById(Long id) {
        return this.interfaceHeaderRepo.getOne(id);
    }

    private List<Map<String, String>> mapRes1= new ArrayList<>();
    @Override
    public PingRes testPing(InterfaceHeader h, String IP, int c) {

        ChkNetwrk chkNetwrk = new ChkNetwrk();

//        InterfaceHeader lh=this.interfaceHeaderRepo.getOne(h.getId());
        PingRes pr = new PingRes();
        String lIp = h.getIpAddress().toString();
        Map<String, String> mapRes = new HashMap<>();


        if (chkNetwrk.isInterfaceOnline(lIp)==true) {
            pr.setResults(this.mikrotikActivityService.getPingRes(h.getId(), IP, c));
        } else {
//            mapRes.put("msg", h.getName() + " is Not Online");
//            mapRes1.add(mapRes );
//            pr.setResults(mapRes1);
            pr.setMsg("Interface -"+h.getName()+"; Ip Address -"+h.getIpAddress() + " is Not Connected!!");
        }
        return pr;
//        List<PingRes> pingRes = new ArrayList<>();

//        for (int i = 0; i < 4; i++) {

//            pingRes.add(pr);
//        }


    }


    @Override
    public void save(InterfaceHeader interfaceHeader, String mode) {
        CommonColumn cc;
        if (mode.equals("E")) {
            cc = new CommonColumn();
        } else {
            cc = interfaceHeader.getColumn();
        }
        this.setCommonColumn(cc, mode);
        interfaceHeader.setColumn(cc);
        this.interfaceHeaderRepo.save(interfaceHeader);
    }

    @Override
    public void delete(InterfaceHeader interfaceHeader) {
        this.interfaceHeaderRepo.delete(interfaceHeader);
    }

    @Override
    public void getInterfacePPPoE(InterfaceHeader interfaceHeader)
    {
        List<InterfaceLine> interfaceLines = new ArrayList<>();
        this.mapRes1=new ArrayList<>();
      this.mapRes1=  this.mikrotikActivityService.getAllPPPoe(interfaceHeader.getId());
        for(Map<String, String> m :this.mapRes1)
        {

            System.out.println(m.get("name")+"<----");
            System.out.println(m.get(".id").substring(1)+"<----");
            Long pppoeId = Long.parseLong(m.get(".id").substring(1));
            if (this.interfaceLineRepo.findByInterfaceHeaderAndPpoeId(interfaceHeader,pppoeId)==null)
            {
                InterfaceLine il = new InterfaceLine();
                il.setInterfaceHeader(interfaceHeader);
                il.setPpoe(m.get("name"));
                il.setProfile(m.get("profile"));
                il.setPpoeId(pppoeId);
                il.setStartDates(new Date());
//                this.interfaceLineRepo.save(il);
                interfaceHeader.getInterfaceLines().add(il);
//                interfaceLines.add(il);
            }

        }

        for (InterfaceLine interfaceLine:interfaceHeader.getInterfaceLines())
        {
            CommonColumn cc= new CommonColumn();
            System.out.println(interfaceLine.getId());
            if (interfaceLine.getId()==null)
            {
                this.setCommonColumn(cc, "E");
                interfaceLine.setColumn(cc);

                System.out.println(interfaceLine.getPpoe());
                interfaceLine.setInterfaceHeader(interfaceHeader);
                this.interfaceLineRepo.save(interfaceLine);

            }
        }

    }
    @Override
    public Page<InterfaceHeader> search(InterfaceHeader i, Pageable p) {
        return this.interfaceHeaderRepo.search(i.getId(), i.getType(), i.getName(), i.getIpAddress(), i.getInterFaceUserName(), i.getInterFaecStatus(), p);
    }


    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode);
    }
}
