package com.isp.billing.service.DashBoard;

import com.isp.billing.model.DashBoard.DBClientInfos;
import com.isp.billing.model.DashBoard.DBInterFaceHeader;

import java.util.List;

/**
 * Created by Toufiq on 8/4/2019.
 */
public interface DBInterFaceHeaderService {
    List<DBInterFaceHeader> findAll();
    void updateDashBoard();
    List<DBClientInfos> findAllClientInfos();
    void updateClientInfosDashBoard();
}
