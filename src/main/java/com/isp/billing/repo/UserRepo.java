package com.isp.billing.repo;

import com.isp.billing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 7/20/2019.
 */
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUserName(String uName);
    List<User> findByUserNameIsContaining(String uName);
}
