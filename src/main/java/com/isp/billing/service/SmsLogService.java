package com.isp.billing.service;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.sms.SmsLog;

import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
public interface SmsLogService {
    List<SmsLog> findAll();


    SmsLog findById(Long id);

    void save(SmsLog smsLog);

    void delete(SmsLog smsLog);
    Long countSmsBySentDatesBetweenAndClientInfo(Date f,Date t,ClientInfo clientInfo);
    Long countSmsBySentDatesBetweenAndClientInfoAndStatus(Date f,Date t,ClientInfo clientInfo,String status);

}
