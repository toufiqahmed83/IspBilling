package com.isp.billing.service;


import com.isp.billing.model.Role;
import com.isp.billing.model.User;
import com.isp.billing.repo.RoleRepo;
import com.isp.billing.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Toufiq on 7/20/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username+"<------");
        String name = username.toLowerCase();
        User user = findByName(name);

        if (user != null) {
//            return new UserAuth(user);
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),user.getPassword(),user.getGrantedAuthorities()
            );
        } else {
            throw new UsernameNotFoundException(username);
        }


    }

    @Override
    public User findByName(String name) {
        return this.userRepo.findByUserName(name);
    }



    @Override
    public void update(User user) throws Exception {

        System.out.println("Save");
        this.userRepo.save(user);

        // TODO Auto-generated method stub

    }

    @Override
    public List<Role> findAllRole() {
        return this.roleRepo.findAll();
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        List<User> allUser = this.userRepo.findAll();
//        for (User us : allUser) {
////            System.out.println(us.getFirstName());
//        }

        return allUser;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
//        if (clientInfos!=null)
//        {
//            setUserData(user,clientInfos);
//        }
        this.userRepo.save(user);
    }

    @Override
    public User findByid(Long id) {
        // TODO Auto-generated method stub
        return this.userRepo.getOne(id);
    }

    @Override
    public List<User> findLikeName(String Name) {
        // TODO Auto-generated method stub

        List<User> listUs = this.userRepo.findByUserNameIsContaining(Name.toLowerCase());
        return listUs;
    }

//    private void setUserData(User userData,ClientInfos clientInfos)
//    {
//        userData.setUserName(clientInfos.getName().toLowerCase());
//        userData.setPassword(getCsiNumber(clientInfos));
//        userData.setFirstName(clientInfos.getName());
//        userData.setLastName(clientInfos.getName());
//        userData.setActive(true);
//        userData.setStartDates(new Date());
//        LinkedHashSet<Role> roles=new LinkedHashSet<Role>();
//        roles.add(this.roleRepo.findByRole("USER"));
//        userData.setRoles(roles);
//
//
//
//
//
//    }
//
//    @Autowired
//    private CsiService csiService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    private String getCsiNumber(ClientInfos clientInfos)
//    {
//        ClientServiceIdentifications csi =this.csiService.findByClient(clientInfos);
//
//        return bCryptPasswordEncoder.encode(csi.getCsiNumber());
//    }

}
