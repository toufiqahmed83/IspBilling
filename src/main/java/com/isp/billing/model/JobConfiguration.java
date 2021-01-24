package com.isp.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Toufiq on 8/5/2019.
 */
@Entity
@Table(name = "JOB_CONFIGURATION")
@Data
public class JobConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CONFIG_KEY")
    private String configKey;

    @Column(name="CONFIG_VALUE")
    private String value;

    @Column(name="Status")
    private Boolean status;

    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;
}
