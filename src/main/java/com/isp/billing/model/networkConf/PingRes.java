package com.isp.billing.model.networkConf;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toufiq on 8/1/2019.
 */
@Data
public class PingRes {
    List<Map<String, String>> results;
    String msg;



}
