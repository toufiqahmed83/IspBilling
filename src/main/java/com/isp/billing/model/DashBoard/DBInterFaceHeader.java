package com.isp.billing.model.DashBoard;

import com.isp.billing.model.networkConf.InterfaceHeader;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Toufiq on 8/4/2019.
 */
@Entity
@Table(name = "DB_NET_INTRF")
@Data
public class DBInterFaceHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "INTERFACE_TYPE")
    private InterfaceHeader.InterfaceType type;

    @Column(name = "INTERFACE_NAME")
    private String name;

    @Column(name = "INTERFACE_IP")
    private String ipAddress;

    @Column(name = "INTERFACE_USER_NAME")
    private String interFaceUserName;

    @Column(name = "INTERFACE_PASS")
    private String interFacePass;

    @Column(name = "STATUS")
    private Boolean interFaecStatus;

    @Column(name = "LINK_STATUS")
    private Boolean linkStatus;

}
