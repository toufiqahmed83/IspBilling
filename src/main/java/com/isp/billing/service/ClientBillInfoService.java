package com.isp.billing.service;

import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
public interface ClientBillInfoService {

    List<ClientBillInfo> finAll();

    ClientBillInfo findBayId(Long id);
    public Page<ClientBillInfo> Search(Search search,Pageable p);

    List<ClientBillInfo> findByClient(ClientInfo clientInfo);

    void save(ClientBillInfo clientBillInfo);

    void delete(ClientBillInfo clientBillInfo);
    Long countBillByBillDateBetweenAndSmsStatus(Date f,Date t,Boolean b);
    Long countBillByBillDateBetweenAndBillStatus(Date f,Date t,Boolean b);
    Long countBillByBillDateBetweenAndClientInfo(Date f,Date t,ClientInfo clientInfo);

}
