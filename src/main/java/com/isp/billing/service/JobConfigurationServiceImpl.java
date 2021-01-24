package com.isp.billing.service;

import com.isp.billing.model.JobConfiguration;
import com.isp.billing.repo.JobConfigurationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toufiq on 8/5/2019.
 */
@Service
public class JobConfigurationServiceImpl //implements JobConfigurationService
 {
    @Autowired
    private JobConfigurationRepo jobConfigurationRepo;

//    @Override
    public List<JobConfiguration> finAll() {
        return this.jobConfigurationRepo.findAll();
    }

//    @Override
    public List<JobConfiguration> finByStatus(Boolean b) {
        return this.jobConfigurationRepo.findByStatus(b);
    }

//    @Override
    public JobConfiguration findById(Long id) {
        return this.jobConfigurationRepo.getOne(id);
    }

//    @Override
    public void save(JobConfiguration j) {
        this.jobConfigurationRepo.save(j);
    }

//    @Override
    public void delete(JobConfiguration j) {
        this.jobConfigurationRepo.delete(j);
    }

    public Map<String, JobConfiguration> jobConfigurationMap = new HashMap<>();
    public List<JobConfiguration> jobConfigurations= new ArrayList<>();
    @PostConstruct
    public void loadConfigurations() {
//        LOGGER.debug("Scheduled Event: Configuration table loaded/updated from database");
        List<JobConfiguration> configs = new ArrayList<>();
//        jobConfigurations= this.jobConfigurationRepo.findAll();
        jobConfigurations= this.jobConfigurationRepo.findByStatus(true);
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration Parameters:");
        configs = this.jobConfigurationRepo.findByStatus(true);
        for (JobConfiguration configuration : configs) {
//            sb.append("\n" + configuration.getConfigKey() + ":" + configuration.getValue());
            this.jobConfigurationMap.put(configuration.getConfigKey(), configuration);
        }
//        LOGGER.debug(sb.toString());
//
//        checkMandatoryConfigurations();
    }

//    @Override
    public JobConfiguration getJobConfig(String configKey)
    {
        jobConfigurationMap.put("ABC",this.findById(1L));
        return this.jobConfigurationMap.get(configKey);
    }
}
