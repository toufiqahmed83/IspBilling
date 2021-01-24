package com.isp.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.CommonColumn;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Toufiq on 7/28/2019.
 */
@Entity
@Table(name = "CLIENT_BILL_INFO")
@Data
public class ClientBillInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientInfo clientInfo;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "phone")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Bill_Date")//, nullable = false
    @Temporal(TemporalType.DATE)
    private Date billDate;

    @Column(name="BILL_AMOUNT")
    private Double amount;

    @Column(name="SMS_STATUS")
    private Boolean smsStatus;

    @Column(name="BILL_STATUS")
    private Boolean billStatus;


    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;


}
