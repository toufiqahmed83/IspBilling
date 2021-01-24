package com.isp.billing.service;

import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.SmsTemplate;
import com.isp.billing.repo.SmsTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Iqbal on 7/27/2019.
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateRepo smsTemplateRepo;

    @Autowired
    private CommonColumnServiceImpl commonColumnService;
    @Override
    public List<SmsTemplate> findAll() {
        return this.smsTemplateRepo.findAll();
    }

    @Override
    public SmsTemplate findById(Long Id) {
        return this.smsTemplateRepo.getOne(Id);

    }

    @Override
    public void saveSms(SmsTemplate smsTemplate, String mode) {
        CommonColumn cc;
        if (mode.equals("E"))
        {
            cc= new CommonColumn();
        }else
        {
            cc=smsTemplate.getColumn();

        }

        this.setCommonColumn(cc,mode);
        smsTemplate.setColumn(cc);
        this.smsTemplateRepo.save(smsTemplate);
    }



    @Override
    public void deleteSms(SmsTemplate smsTemplate) {
    this.smsTemplateRepo.delete(smsTemplate);
    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode);
    }
}
