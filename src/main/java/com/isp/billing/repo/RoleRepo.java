package com.isp.billing.repo;


import com.isp.billing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 7/20/2019.
 */
public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByRole(String role);
}
