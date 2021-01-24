package com.isp.billing.service;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;

import java.util.List;

/**
 * Created by Toufiq on 7/31/2019.
 */
public interface InterfaceLineService {

    List<InterfaceLine> findAll();

    InterfaceLine findById(Long id);

    void save(InterfaceLine i,String mode);
    void savelines(InterfaceHeader H,String mode);
    void delete(InterfaceLine i);
}
