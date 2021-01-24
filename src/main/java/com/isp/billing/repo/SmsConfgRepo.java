package com.isp.billing.repo;

import com.isp.billing.model.sms.SmsConfg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
public interface SmsConfgRepo extends JpaRepository<SmsConfg,Long>{

    List<SmsConfg> findByStatus(Boolean status);
}
