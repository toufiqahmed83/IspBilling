package com.isp.billing.model.DashBoard;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Toufiq on 8/19/2019.
 */
@Entity
@Table(name="DB_CLIENT_INTRF")
@Data
public class DBClientInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENT_COUNT")
    private Long totClient;

    @Column(name = "SMS_DELV_COUNT")
    private Long totSmsDelv;

    @Column(name = "BILL_RCV_COUNT")
    private Long totBillCollect;

}
