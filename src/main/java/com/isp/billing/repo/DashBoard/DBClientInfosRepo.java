package com.isp.billing.repo.DashBoard;

import com.isp.billing.model.DashBoard.DBClientInfos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 8/19/2019.
 */
public interface DBClientInfosRepo extends JpaRepository<DBClientInfos,Long> {
}
