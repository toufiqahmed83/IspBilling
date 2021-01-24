package com.isp.billing.repo.DashBoard;

import com.isp.billing.model.DashBoard.DBInterFaceHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 8/4/2019.
 */
public interface DBInterFaceHeaderRepo extends JpaRepository<DBInterFaceHeader,Long> {
    DBInterFaceHeader findByName(String s);
//    DBInterFaceHeader findByNameAndDbTag(String s,DBInterFaceHeader.dbTag d);
//    List<DBInterFaceHeader> findByDbTag(DBInterFaceHeader.dbTag d);
}
