package com.isp.billing.repo;

import com.isp.billing.model.networkConf.InterfaceHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Toufiq on 7/31/2019.
 */
public interface InterfaceHeaderRepo extends JpaRepository<InterfaceHeader,Long> {

    @Query("select x from InterfaceHeader x where (:id is null or x.id=:id) and " +
            "(:type is null or x.type=:type) and " +
            "(:name is null or x.name=:name) and " +
            "(:ipAddress is null or x.ipAddress=:ipAddress) and " +
            "(:interFaceUserName is null or x.interFaceUserName=:interFaceUserName) and " +
            "(:interFaecStatus is null or x.interFaecStatus=:interFaecStatus) " )
    Page<InterfaceHeader> search(@Param("id")Long id,@Param("type")InterfaceHeader.InterfaceType type,
                                 @Param("name")String name,@Param("ipAddress")String ipAddress,@Param("interFaceUserName")String interFaceUserName,
                                 @Param("interFaecStatus")Boolean interFaecStatus,
                                 Pageable p);
}
