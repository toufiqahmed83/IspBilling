package com.isp.billing.service;


import com.isp.billing.model.Role;
import com.isp.billing.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Toufiq on 7/20/2019.
 */
public interface UserService extends UserDetailsService {

    public User findByName(String name);

    public User findByid(Long id);

    public List<User> findAll();


    public List<User> findLikeName(String Name);
    public void update(User user) throws Exception;

    void saveUser(User user);

    public List<Role> findAllRole();
}
