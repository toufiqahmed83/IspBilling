package com.isp.billing.service;


import com.isp.billing.model.CommonColumn;
import com.isp.billing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 7/21/2019.
 */
@Service
public class CommonColumnServiceImpl {
    @Autowired
    private UserService userService;

    public CommonColumn setCommonColumn(CommonColumn commonColumn,String mode)
    {
        Authentication auths = SecurityContextHolder.getContext().getAuthentication();
//        CommonColumn commonColumn=new CommonColumn();
        User curUser = this.userService.findByName(auths.getName());
        System.out.println(curUser.getId()+"curUser");
        if (mode.equals("E")) {
            System.out.println("E");
            commonColumn.setCreatedBy(curUser.getId());
            commonColumn.setCreationDate(new Date());
        } else {

            System.out.println(commonColumn + "<--commonColumn-->" + curUser.getId());
            commonColumn.setLastUpdateBy(curUser.getId());
            commonColumn.setLastUpdateDate(new Date());


        }


        return commonColumn;
    }


    public CommonColumn setCommonColumnInternal(CommonColumn commonColumn,String mode)
    {
//        Authentication auths = SecurityContextHolder.getContext().getAuthentication();
//        CommonColumn commonColumn=new CommonColumn();
        User curUser = this.userService.findAll().get(0);
        System.out.println(curUser.getId()+"curUser");
        if (mode.equals("E")) {
            System.out.println("E");
            commonColumn.setCreatedBy(curUser.getId());
            commonColumn.setCreationDate(new Date());
        } else {

            System.out.println(commonColumn + "<--commonColumn-->" + curUser.getId());
            commonColumn.setLastUpdateBy(curUser.getId());
            commonColumn.setLastUpdateDate(new Date());


        }


        return commonColumn;
    }
}
