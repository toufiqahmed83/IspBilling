package com.isp.billing.repo;


import com.isp.billing.model.SmsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Iqbal on 7/27/2019.
 */
public interface SmsTemplateRepo extends JpaRepository<SmsTemplate,Long> {
}
