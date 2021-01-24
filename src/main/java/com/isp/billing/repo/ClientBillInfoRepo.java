package com.isp.billing.repo;

import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.networkConf.InterfaceLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 7/28/2019.
 */
public interface ClientBillInfoRepo  extends JpaRepository<ClientBillInfo,Long>{

    List<ClientBillInfo> findByClientInfo(ClientInfo clientInfo);
    List<ClientBillInfo> findAllByBillDateBetween(Date f,Date t);
    Long countByBillDateBetweenAndSmsStatus(Date f,Date t,Boolean b);
    Long countByBillDateBetweenAndBillStatus(Date f,Date t,Boolean b);
    Long countByBillDateBetweenAndClientInfo(Date f,Date t,ClientInfo clientInfo);


    @Query("select x from ClientBillInfo x where (:id is null or x.clientInfo.id=:id) and " +
            "(:name is null or x.clientInfo.name=:name) and " +
            "(:phone is null or x.clientInfo.phone=:phone) and "+
            "(:ppoe is null or x.clientInfo.ppoe=:ppoe) and "+
            "(:billCycleDate is null or x.clientInfo.billCycleDate=:billCycleDate)  and "+
            "((:fromDate is null or :toDate is null) or  x.billDate between :fromDate and :toDate)  and "+
            "(:smsStatus is null or x.smsStatus=:smsStatus)  and "+
            "(:billStatus is null or x.billStatus=:billStatus) ")
    Page<ClientBillInfo> search(@Param("id")Long id ,@Param("name")String name ,@Param("phone")String phone ,
                                @Param("ppoe")String ppoe ,@Param("billCycleDate")Integer billCycleDate ,
                                @Param("fromDate")Date fromDate ,@Param("toDate")Date toDate ,@Param("smsStatus")Boolean smsStatus ,
                                @Param("billStatus")Boolean billStatus ,Pageable p) ;
}
