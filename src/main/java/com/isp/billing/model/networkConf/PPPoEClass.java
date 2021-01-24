package com.isp.billing.model.networkConf;

import lombok.Data;

/**
 * Created by Toufiq on 8/11/2019.
 */
@Data
public class PPPoEClass {
    private String limitBytesIn;
    private String routes;
    private String password;
    private String callerId;
    private String service;
    private String lastLoggedOut;
    private String profile;
    private String name;
    private String id;
    private String limitBytesOut;
    private Boolean disabled;
}
