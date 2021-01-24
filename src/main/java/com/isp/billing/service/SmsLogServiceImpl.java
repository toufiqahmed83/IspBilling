package com.isp.billing.service;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.sms.SmsLog;
import com.isp.billing.repo.SmsLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
@Service
public class SmsLogServiceImpl  implements SmsLogService{

    @Autowired
    private SmsLogRepo smsLogRepo;

    @Override
    public List<SmsLog> findAll() {
        return this.smsLogRepo.findAll();
    }

    @Override
    public SmsLog findById(Long id) {
        return this.smsLogRepo.getOne(id);
    }

    @Override
    public void save(SmsLog smsLog) {
        this.smsLogRepo.save(smsLog);
    }

    @Override
    public void delete(SmsLog smsLog) {
        this.smsLogRepo.delete(smsLog);
    }

    @Override
    public Long countSmsBySentDatesBetweenAndClientInfo(Date f, Date t, ClientInfo clientInfo) {
        return this.smsLogRepo.countBySentDatesBetweenAndClientInfo(f,t,clientInfo);
    }

    @Override
    public Long countSmsBySentDatesBetweenAndClientInfoAndStatus(Date f, Date t, ClientInfo clientInfo, String status) {
        return this.smsLogRepo.countBySentDatesBetweenAndClientInfoAndStatus(f,t,clientInfo,status);
    }
}
