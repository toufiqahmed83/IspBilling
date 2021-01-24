package com.isp.billing.repo;

import com.isp.billing.model.JobConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 8/5/2019.
 */
public interface JobConfigurationRepo extends JpaRepository<JobConfiguration,Long>{

    List<JobConfiguration> findByStatus(Boolean b);
}
