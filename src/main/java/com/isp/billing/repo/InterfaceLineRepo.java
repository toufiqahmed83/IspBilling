package com.isp.billing.repo;

import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.InterfaceLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 7/31/2019.
 */
public interface InterfaceLineRepo extends JpaRepository<InterfaceLine,Long> {
    InterfaceLine findByInterfaceHeaderAndPpoeId(InterfaceHeader interfaceHeader,Long p);
}
