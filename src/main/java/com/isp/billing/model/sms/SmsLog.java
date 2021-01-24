package com.isp.billing.model.sms;

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
@Table(name = "sms_log")
//@Getter @Setter @NoArgsConstructor
@Data
public class SmsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name = "UNIQUE_ID")
    private String uniqueId;

    @Column(name = "CLIENT_NAME")
    private String clntName;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientInfo clientInfo;

    @Column(name = "PHONE_NO")
    private String phnno;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SMS_STATUS")
    private String smsStatus;

    @Column(name = "SMS_STATUS_MSG")
    private String smsStatusMsg;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "SEND_DATE")//, nullable = false
    @Temporal(TemporalType.DATE)
    private Date sentDates;

    @Column(name = "MSG")
    private String msg;

    @Column(name = "TRANSACTION_NUMBER")
    private String trnNum;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;

}
