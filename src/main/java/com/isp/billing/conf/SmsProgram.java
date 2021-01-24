package com.isp.billing.conf;

//import com.aye.smsapi.smsapi.SmsApi;

import com.aye.smsapi.smsapi.SmsApi;
import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.ClientInfo;
import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.sms.SmsConfg;
import com.isp.billing.model.sms.SmsLog;
import com.isp.billing.service.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Toufiq on 7/27/2019.
 */
//@SpringBootApplication
@Service
public class SmsProgram //implements CommandLineRunner
{

    @Autowired
    private CommonColumnServiceImpl commonColumnService;

    @Autowired
    private SmsLogService smsLogService;

    @Autowired
    private ClientInfoService clientInfoService;


    @Autowired
    private ClientBillInfoService clientBillInfoService;

    @Autowired
    private SmsConfgService smsConfgService;

    private JSONObject object = null;

    private SmsApi smsApi = new SmsApi();

    public JSONObject getSmsApi(String userName, String pass, String url, String phnNo, String msg, String cli) {
        try {
            object = smsApi.postSms(userName, pass, url, phnNo, msg, cli);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return object;
    }


    public void actions() {
        Calendar cl = Calendar.getInstance();

        int day = cl.get(Calendar.DAY_OF_MONTH);
        System.out.println(day + "<--day");
        for (ClientInfo ci : this.clientInfoService.findByStatus(true)) {
            if (ci.getRcvSms()) {
                if (ci.getBillCycleDate() != null && ci.getBillCycleDate().equals(day) && ci.getSmsTemplate() != null) {
                    if (!checkSmsSent(ci)) {
                        System.out.println(ci.getFirstName());
                        ClientBillInfo cb = new ClientBillInfo();
                        SmsLog sl = new SmsLog();
                        setbillandSmsLogData(ci, sl, cb);
                        smsAction(ci, sl, cb);

                    }
                }
            } else {
                if (ci.getBillCycleDate() != null && ci.getBillCycleDate().equals(day) && ci.getSmsTemplate() == null) {
                    if (!checkBill(ci)) {
                        ClientBillInfo cb = new ClientBillInfo();
                        billLog(ci, cb);

                    }
                }
            }

        }
    }

    private void setbillandSmsLogData(ClientInfo clientInfo, SmsLog smsLog, ClientBillInfo clientBillInfo) {
//        ClientBillInfo clientBillInfo = new ClientBillInfo();
//        SmsLog smsLog = new SmsLog();

        clientBillInfo.setAmount(clientInfo.getAmount());
        clientBillInfo.setBillDate(new Date());
        clientBillInfo.setClientInfo(clientInfo);
        clientBillInfo.setClientName(clientInfo.getName());
        clientBillInfo.setPhone(clientInfo.getPhone());
        clientBillInfo.setBillStatus(false);
        clientBillInfo.setSmsStatus(false);

        smsLog.setClientInfo(clientInfo);
        smsLog.setClntName(clientInfo.getName());
        smsLog.setPhnno(clientInfo.getPhone());
        smsLog.setSmsStatus("N");
        smsLog.setStatus("N");

        CommonColumn cc = new CommonColumn();
        setCommonColumn(cc, "E");

        clientBillInfo.setColumn(cc);
        smsLog.setColumn(cc);

        this.clientBillInfoService.save(clientBillInfo);
        this.smsLogService.save(smsLog);


    }


    private void billLog(ClientInfo clientInfo, ClientBillInfo clientBillInfo) {
        clientBillInfo.setAmount(clientInfo.getAmount());
        clientBillInfo.setBillDate(new Date());
        clientBillInfo.setClientInfo(clientInfo);
        clientBillInfo.setClientName(clientInfo.getName());
        clientBillInfo.setPhone(clientInfo.getPhone());
        clientBillInfo.setBillStatus(false);
        clientBillInfo.setSmsStatus(false);
        CommonColumn cc = new CommonColumn();
        setCommonColumn(cc, "E");
        clientBillInfo.setColumn(cc);
        this.clientBillInfoService.save(clientBillInfo);


    }


    private void smsAction(ClientInfo clientInfo, SmsLog smsLog, ClientBillInfo clientBillInfo) {
        SmsConfg sc = this.smsConfgService.findSmsConfg();
        JSONObject lJsonObject = null;
        try {
            lJsonObject = smsApi.postSms(sc.getName(), sc.getPass(), sc.getUrl(), clientInfo.getPhone(), clientInfo.getSmsTemplate().getMsg(), sc.getCli());
//            System.out.println(lJsonObject + " <---lJsonObject");

            for (int i = 0; i < lJsonObject.names().length(); i++) {
//                System.out.println("loops");
                String key = lJsonObject.names().getString(i);
                Object key1 = lJsonObject.get(lJsonObject.names().getString(i));

//								if(!(key.equals(400)&&key1.equals(null)))
//								{
//								System.out.println(key+"  key "+key1);
//								}
                if (!(key.equals(400) && key1.equals(null))) {
//									System.out.println("B4");
                    this.smsLogService.save(setData(lJsonObject, smsLog, clientBillInfo));
                    this.clientBillInfoService.save(clientBillInfo);
//                    System.out.println("key1---- " + key1);
                }
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


    }


    public SmsLog setData(JSONObject object, SmsLog smsData, ClientBillInfo clientBillInfo) throws JSONException {
        for (int i = 0; i < object.names().length(); i++) {
            String key = object.names().getString(i);
            Object key1 = object.get(object.names().getString(i));
            if (key.equals("200")) {
                smsData.setSmsStatus("Y");
                smsData.setTrnNum((String) key1);
                clientBillInfo.setSmsStatus(true);
            } else {
                smsData.setSmsStatus("N");
                clientBillInfo.setSmsStatus(false);
            }
            smsData.setSmsStatusMsg((String) key1);
            smsData.setStatus("Y");
            smsData.setSentDates(new Date());
        }
        return smsData;
    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode) {
        return this.commonColumnService.setCommonColumnInternal(commonColumn, mode);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    private Boolean checkSmsSent(ClientInfo clientInfo) {
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sFirst = firstDayofCurrentMonth.format(formatter);
        String sLast = lastDayofCurrentMonth.format(formatter);

        Date f = java.sql.Date.valueOf(sFirst);
        Date t = java.sql.Date.valueOf(sLast);
        if (this.smsLogService.countSmsBySentDatesBetweenAndClientInfo(f, t, clientInfo) > 0) {

            return true;
        }
        return false;
    }

    private Boolean checkBill(ClientInfo clientInfo) {
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sFirst = firstDayofCurrentMonth.format(formatter);
        String sLast = lastDayofCurrentMonth.format(formatter);

        Date f = java.sql.Date.valueOf(sFirst);
        Date t = java.sql.Date.valueOf(sLast);
        System.out.println(f + " " + t + " " + clientInfo.getName());
        if (this.clientBillInfoService.countBillByBillDateBetweenAndClientInfo(f, t, clientInfo) > 0) {

            return true;
        }
        return false;
    }

}
