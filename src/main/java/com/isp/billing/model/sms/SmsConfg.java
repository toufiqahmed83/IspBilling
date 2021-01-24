package com.isp.billing.model.sms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isp.billing.model.CommonColumn;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Toufiq on 7/27/2019.
 */
@Entity
@Table(name = "SMS_CONFIG")
@Data
public class SmsConfg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "SMS_USER_NAME")
    private String name;
    @Column(name = "SMS_USER_PASS")
    private String pass;
    @Column(name = "SMS_URL")
    private String url;
    @Column(name = "SMS_MASKING")
    private String cli;
    @Column(name = "STATUS")
    private boolean status;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;
}
