package com.isp.billing.service;

import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;
import com.isp.billing.repo.InterfaceLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Toufiq on 7/31/2019.
 */
@Service
public class InterfaceLineServiceImpl implements InterfaceLineService{

    @Autowired
    private InterfaceLineRepo interfaceLineRepo;


    @Autowired
    private CommonColumnServiceImpl  commonColumnService;


    @Override
    public List<InterfaceLine> findAll() {
        return this.interfaceLineRepo.findAll();

    }

    @Override
    public InterfaceLine findById(Long id) {
        return this.interfaceLineRepo.getOne(id);
    }

    @Override
    public void save(InterfaceLine i,String mode) {
        CommonColumn cc;

        if (mode.equals("E"))
        {
            cc=new CommonColumn();
        }else
        {
            cc= i.getColumn();
        }
        this.setCommonColumn(cc,mode);
        i.setColumn(cc);

        this.interfaceLineRepo.save(i);
    }

    @Override
    public void savelines(InterfaceHeader H, String mode) {
        for (InterfaceLine il : H.getInterfaceLines())
        {
            il.setInterfaceHeader(H);
            if (il.getId()==null)
            {

                save(il,"E");
            }else
            {
                save(il,"U");
            }
        }
    }

    @Override
    public void delete(InterfaceLine i) {
        this.interfaceLineRepo.delete(i);
    }
    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode);
    }


}
