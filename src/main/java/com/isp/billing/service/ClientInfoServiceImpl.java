package com.isp.billing.service;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.repo.ClientInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Iqbal on 7/25/2019.
 */
@Service
public class ClientInfoServiceImpl implements ClientInfoService {
    @Autowired
    private ClientInfoRepo clientInfoRepo;

    @Autowired
    private MikrotikActivityServiceImpl mikrotikActivityService;


    @Autowired
    private CommonColumnServiceImpl commonColumnService;

    @Override
    public List<ClientInfo> findAll() {
        return this.clientInfoRepo.findAll();
//        return this.clientInfoRepo.findByActive(true);
    }

    @Override
    public List<ClientInfo> findByStatus(Boolean aBoolean) {
        return this.clientInfoRepo.findByActive(aBoolean);
    }

    @Override
    public ClientInfo findById(Long Id) {
        return this.clientInfoRepo.getOne(Id);
    }

    @Override
    public List<ClientInfo> findByName(String name) {
        return this.clientInfoRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void saveClient(ClientInfo clientInfo, String mode) {
        CommonColumn cc;
        if (mode.equals("E"))
        {
            cc= new CommonColumn();
        }else
        {
            cc=clientInfo.getColumn();
        }

        this.setCommonColumn(cc,mode);
        clientInfo.setColumn(cc);

        this.clientInfoRepo.save(clientInfo);

        if (clientInfo.getInterfaceLine()!=null) {
            this.mikrotikActivityService.changeStatus(clientInfo.getActive(), clientInfo.getInterfaceLine().getId());
        }
//        if (clientInfo.getActive()==false)
//        {
//
//            ///call mikrotik
//        }

    }


    @Override
    public void deletClient(ClientInfo clientInfo) {
        this.clientInfoRepo.delete(clientInfo);
    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode);
    }
}
