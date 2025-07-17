package com.easybank.fbp.loans;

import com.easybank.fbp.loans.dto.AppProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@OpenAPIDefinition(info = @Info(
		title = "LOANS-SERVICE",
		description = "EASY-BANK LOANS MICROSERVICE",
		version = "V1",
		summary = "we will get the information about easy-bank accounts for loans"
))
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties(value = {AppProperties.class})
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

	@FeignClient("ACCOUNTS-SERVICE-DEV")
	static interface AccountsClient {

	}

}
