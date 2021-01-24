package com.isp.billing.service;

import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.sms.SmsConfg;
import com.isp.billing.repo.SmsConfgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
@Service
public class SmsConfgService {

    @Autowired
    private SmsConfgRepo SmsConfgRepo;

    @Autowired
    private CommonColumnServiceImpl commonColumnService;

    public SmsConfg findSmsConfg()
    {
        return this.SmsConfgRepo.findByStatus(true).get(0);
    }


    public List<SmsConfg> findAll()
    {
        return this.SmsConfgRepo.findAll();
    }
    public SmsConfg findById(Long Id) {return this.SmsConfgRepo.getOne(Id);}

    public void save(SmsConfg s,String mode)
    {        CommonColumn cc;

        if (s.getId()==null)
        {
            cc=new CommonColumn();
        }else
        {
            cc= s.getColumn();
        }
        this.setCommonColumn(cc,mode);
        s.setColumn(cc);

        this.SmsConfgRepo.save(s);
    }


    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode);
    }

}
