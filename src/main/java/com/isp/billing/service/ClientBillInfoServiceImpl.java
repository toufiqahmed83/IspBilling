package com.isp.billing.service;

import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.Search;
import com.isp.billing.repo.ClientBillInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
@Service
public class ClientBillInfoServiceImpl  implements ClientBillInfoService{
    @Autowired
    private ClientBillInfoRepo clientBillInfoRepo;


    @Override
    public List<ClientBillInfo> finAll() {
        return this.clientBillInfoRepo.findAll();
    }

    @Override
    public ClientBillInfo findBayId(Long id) {
        return this.clientBillInfoRepo.getOne(id);
    }

    @Override
    public List<ClientBillInfo> findByClient(ClientInfo clientInfo) {
        return this.clientBillInfoRepo.findByClientInfo(clientInfo);
    }

    @Override
    public Page<ClientBillInfo> Search(Search search,Pageable p)
    {
        return this.clientBillInfoRepo.search(search.getId(),search.getName(),
                search.getPhone(),search.getPpoe(),search.getBillCycleDate(),
                search.getFromDate(),search.getToDate(),
                search.getSmsStatus(),search.getBillStatus(),p);
    }

    @Override
    public void save(ClientBillInfo clientBillInfo) {
        this.clientBillInfoRepo.save(clientBillInfo);
    }

    @Override
    public void delete(ClientBillInfo clientBillInfo) {
        this.clientBillInfoRepo.delete(clientBillInfo);
    }

    @Override
    public Long countBillByBillDateBetweenAndSmsStatus(Date f, Date t, Boolean b) {
        return this.clientBillInfoRepo.countByBillDateBetweenAndSmsStatus(f, t, b);
    }

    @Override
    public Long countBillByBillDateBetweenAndBillStatus(Date f, Date t, Boolean b) {
        return this.clientBillInfoRepo.countByBillDateBetweenAndBillStatus(f,t,b);
    }

    @Override
    public Long countBillByBillDateBetweenAndClientInfo(Date f, Date t, ClientInfo clientInfo) {
        return this.clientBillInfoRepo.countByBillDateBetweenAndClientInfo(f,t,clientInfo);
    }
}
