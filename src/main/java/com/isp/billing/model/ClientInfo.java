package com.isp.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isp.billing.model.networkConf.InterfaceLine;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Iqbal on 7/25/2019.
 */

@Entity
@Table(name = "client_info")
//@Getter @Setter @NoArgsConstructor
@Data
public class ClientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "First_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "Address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "PPOE_ID")
    private InterfaceLine interfaceLine;

    @Column(name = "ppoe")
    private String ppoe;

    @Column(name = "Amount")
    private Double amount;


    @Column(name = "Bill_Date")
    private Integer billCycleDate;

    @ManyToOne
    @JoinColumn(name = "Sms_Template_Id")
    private SmsTemplate smsTemplate;

    @Column(name = "RCV_SMS")
    private Boolean rcvSms;


    @Column(name = "active")
    private Boolean active;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "START_DATE")//, nullable = false
    @Temporal(TemporalType.DATE)
//    @NotEmpty(message = "Date is required.")
    private Date startDates;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE")//, nullable = false
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDates;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;

}
