package com.isp.billing.model.networkConf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isp.billing.model.CommonColumn;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Toufiq on 7/31/2019.
 */
@Entity
@Table(name = "NETWORK_INTERFACE_LINES")
@Data
public class InterfaceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NIL_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="NIH_ID")
    private InterfaceHeader interfaceHeader;

    @Column(name = "PPOE")
    private String ppoe;

    @Column(name = "PPOE_ID")
    private Long ppoeId;

    @Column(name = "PROFILE")
    private String profile;



    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "START_DATE")//, nullable = false
    @Temporal(TemporalType.DATE)
//    @NotEmpty(message = "Date is required.")
    private Date startDates;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE")//, nullable = false
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDates;


    @Column(name="STATUS")
    private Boolean lineStatus;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;


}
