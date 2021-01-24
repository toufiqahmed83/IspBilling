package com.isp.billing.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.isp.billing.model")
@EnableJpaRepositories(basePackages = {"com.isp.billing.repo"})
@EnableTransactionManagement
@ComponentScan("com.isp.billing")
@EnableScheduling
public class ProjectForIspApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectForIspApplication.class, args);
	}

}
