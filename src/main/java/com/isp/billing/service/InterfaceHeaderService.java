package com.isp.billing.service;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.PingRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Toufiq on 7/31/2019.
 */
public interface InterfaceHeaderService {

    List<InterfaceHeader> findAll();

    InterfaceHeader findById(Long id);
    Page<InterfaceHeader> search(InterfaceHeader i,Pageable p);
    void save(InterfaceHeader interfaceHeader,String mode);
    void delete(InterfaceHeader interfaceHeader);
    PingRes testPing(InterfaceHeader h,String IP,int c);
    void getInterfacePPPoE(InterfaceHeader interfaceHeader);
}
