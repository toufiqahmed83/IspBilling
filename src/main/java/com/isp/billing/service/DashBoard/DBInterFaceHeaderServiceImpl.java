package com.isp.billing.service.DashBoard;

import com.aye.mikrotiklib.NetworkChk;
import com.isp.billing.model.ClientBillInfo;
import com.isp.billing.model.DashBoard.DBClientInfos;
import com.isp.billing.model.DashBoard.DBInterFaceHeader;
import com.isp.billing.model.networkConf.InterfaceHeader;
import com.isp.billing.model.networkConf.PingRes;
import com.isp.billing.repo.ClientBillInfoRepo;
import com.isp.billing.repo.ClientInfoRepo;
import com.isp.billing.repo.DashBoard.DBClientInfosRepo;
import com.isp.billing.repo.DashBoard.DBInterFaceHeaderRepo;
import com.isp.billing.service.ChkNetwrk;
import com.isp.billing.service.ClientBillInfoService;
import com.isp.billing.service.InterfaceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 8/4/2019.
 */
@Service
public class DBInterFaceHeaderServiceImpl implements DBInterFaceHeaderService {


    @Autowired
    private DBInterFaceHeaderRepo dbInterFaceHeaderRepo;

    @Autowired
    private DBClientInfosRepo dbClientInfosRepo;

    @Autowired
    private InterfaceHeaderService interfaceHeaderService;
    @Autowired
    private ClientBillInfoRepo clientBillInfoRepo;

    @Autowired
    private ClientInfoRepo clientInfoRepo;

    @Override
    public List<DBInterFaceHeader> findAll() {

//        return Flux.fromIterable(this.dbInterFaceHeaderRepo.findAll()).delayElements(Duration.ofSeconds(2));
        return this.dbInterFaceHeaderRepo.findAll();
    }


    private static String IP = "4.2.2.2";

    @Override
    public void updateDashBoard() {

        this.dbInterFaceHeaderRepo.deleteAll();
        ChkNetwrk chkNetwrk = new ChkNetwrk();
        for (InterfaceHeader ih : this.interfaceHeaderService.findAll()) {
            DBInterFaceHeader dbh;

//            if (this.dbInterFaceHeaderRepo.findByName(ih.getName())   == null) {
                dbh = new DBInterFaceHeader();
                dbh.setId(ih.getId());
                dbh.setInterFacePass(ih.getInterFacePass());
                dbh.setInterFaceUserName(ih.getInterFaceUserName());
                dbh.setName(ih.getName());
                dbh.setInterFaecStatus(chkNetwrk.isInterfaceOnline(ih.getIpAddress()));
                dbh.setIpAddress(ih.getIpAddress());
                dbh.setType(ih.getType());

                if (dbh.getInterFaecStatus()) {
                    dbh.setLinkStatus(chkInternet(ih));
                } else {
                    dbh.setLinkStatus(false);
                }
                this.dbInterFaceHeaderRepo.save(dbh);
//            } else {
//                dbh = this.dbInterFaceHeaderRepo.findByName(ih.getName());
//                dbh.setInterFaecStatus(chkNetwrk.isInterfaceOnline(ih.getIpAddress()));
//                if (dbh.getInterFaecStatus()) {
//                    dbh.setLinkStatus(chkInternet(ih));
//                } else {
//                    dbh.setLinkStatus(false);
//                }
//
//                this.dbInterFaceHeaderRepo.save(dbh);
//            }
        }
//        updateDBClientBilling();

    }

    @Override
    public List<DBClientInfos> findAllClientInfos() {
        return this.dbClientInfosRepo.findAll();
    }

    @Override
    public void updateClientInfosDashBoard() {
        this.dbClientInfosRepo.deleteAll();
        DBClientInfos dbh;
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sFirst = firstDayofCurrentMonth.format(formatter);
        String sLast = lastDayofCurrentMonth.format(formatter);

        Date f = java.sql.Date.valueOf(sFirst);
        Date t = java.sql.Date.valueOf(sLast);
//
//        if (this.dbClientInfosRepo.findAll().size() == 0) {
            dbh = new DBClientInfos();
//            dbh.setDbTag(DBInterFaceHeader.dbTag.C);
            dbh.setTotSmsDelv(this.clientBillInfoRepo.countByBillDateBetweenAndSmsStatus(f,t,true));
            dbh.setTotBillCollect(this.clientBillInfoRepo.countByBillDateBetweenAndBillStatus(f, t, true));
            dbh.setTotClient(this.clientInfoRepo.countByActive(true));
            this.dbClientInfosRepo.save(dbh);
//        } else {
//            dbh = this.dbClientInfosRepo.findAll().get(0);
//            dbh.setTotSmsDelv(this.clientBillInfoRepo.countByBillDateBetweenAndSmsStatus(f,t,true));
//            dbh.setTotBillCollect(this.clientBillInfoRepo.countByBillDateBetweenAndBillStatus(f, t, true));
//            dbh.setTotClient(this.clientInfoRepo.countByActive(true));
//            this.dbClientInfosRepo.save(dbh);
//        }
    }

    private Boolean chkInternet(InterfaceHeader ih) {
        PingRes r = this.interfaceHeaderService.testPing(ih, IP, 1);
        if (r.getResults().size() > 0) {
            return true;
        } else {
            return false;
        }
    }




}
