package com.isp.billing.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by Toufiq on 7/25/2019.
 */
@Embeddable
@Data
public class CommonColumn {
    @Column(name = "CREATED_BY")//, nullable = false
    private Long createdBy;

    @Column(name = "CREATION_DATE")//, nullable = false
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdateBy;


    @Column(name = "LAST_UPDATED_LOGIN")
    private Long lastUpdateLogin;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
}
