package com.isp.billing.model.networkConf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isp.billing.model.CommonColumn;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toufiq on 7/31/2019.
 */
@Entity
@Table(name = "NETWORK_INTERFACE_HEADER")
@Data
public class InterfaceHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NIH_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "INTERFACE_TYPE")
    private InterfaceType type;

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

    @OneToMany(mappedBy = "interfaceHeader")
    private List<InterfaceLine> interfaceLines = new ArrayList<>();

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;

    public enum InterfaceType{
        Mikrotik,Cisco
    }
}
