package com.isp.billing.service;

import com.isp.billing.model.ClientInfo;

import java.util.List;

/**
 * Created by Iqbal on 7/25/2019.
 */
public interface ClientInfoService {
    List<ClientInfo> findAll();
    List<ClientInfo> findByStatus(Boolean aBoolean);
    ClientInfo findById(Long Id);
    List<ClientInfo> findByName(String name);
    void saveClient(ClientInfo clientInfo, String mode);
    void deletClient(ClientInfo clientInfo);
}
