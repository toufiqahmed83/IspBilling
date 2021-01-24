package com.isp.billing.service;

import com.isp.billing.model.SmsTemplate;

import java.util.List;

/**
 * Created by Iqbal on 7/27/2019.
 */
public interface SmsTemplateService {
    List<SmsTemplate> findAll();
    SmsTemplate findById(Long Id);
    void saveSms (SmsTemplate smsTemplate, String mode);
    void deleteSms(SmsTemplate smsTemplate);
}
