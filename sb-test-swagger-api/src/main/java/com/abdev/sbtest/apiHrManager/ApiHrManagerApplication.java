package com.abdev.sbtest.apiHrManager;

import com.abdev.sbtest.apiHrManager.repository.RapportEmployeRepository;
import com.abdev.sbtest.apiHrManager.services.ApiService;
import com.abdev.sbtest.apiHrManager.utils.DataApplicationFirt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		ApiHrManagerApplication.class,
		Jsr310JpaConverters.class
})
public class ApiHrManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHrManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner	start(ApiService	apiService, RapportEmployeRepository	rapportEmployeRepository, DataApplicationFirt dataApplicationFirt){
		return args -> {
				dataApplicationFirt.executeAll(apiService,rapportEmployeRepository);
		};
	}

}
