package com.isp.billing.repo;

import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.sms.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by Toufiq on 7/28/2019.
 */
public interface SmsLogRepo extends JpaRepository<SmsLog ,Long> {
    Long countBySentDatesBetweenAndClientInfo(Date f,Date t,ClientInfo clientInfo);
    Long countBySentDatesBetweenAndClientInfoAndStatus(Date f,Date t,ClientInfo clientInfo,String status);
}
