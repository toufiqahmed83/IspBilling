package com.isp.billing.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Toufiq on 7/29/2019.
 */
@Data
public class Search {

    private Long id;
    private String name;
    private String phone;
    private String ppoe;
    private Integer billCycleDate;
    private Date fromDate;
    private Date toDate;
    private Boolean smsStatus;
    private Boolean billStatus;



}
