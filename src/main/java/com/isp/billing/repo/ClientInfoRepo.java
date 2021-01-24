package com.isp.billing.repo;

import com.isp.billing.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Iqbal on 7/25/2019.
 */
public interface ClientInfoRepo extends JpaRepository <ClientInfo,Long> {

    List<ClientInfo> findByActive(Boolean aBoolean);
    List<ClientInfo> findByNameContainingIgnoreCase(String name);
    Long countByActive(Boolean b);

}
