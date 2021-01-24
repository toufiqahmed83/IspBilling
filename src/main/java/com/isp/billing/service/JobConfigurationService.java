package com.isp.billing.service;

import com.isp.billing.model.JobConfiguration;

import java.util.List;

/**
 * Created by Toufiq on 8/5/2019.
 */
public interface JobConfigurationService {
    List<JobConfiguration> finAll();
    List<JobConfiguration> finByStatus(Boolean b);

    JobConfiguration findById(Long id);

    void save(JobConfiguration j);

    void delete(JobConfiguration j);
    JobConfiguration getJobConfig(String configKey);
}
