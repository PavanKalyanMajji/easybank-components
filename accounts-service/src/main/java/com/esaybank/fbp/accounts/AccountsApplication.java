package com.esaybank.fbp.accounts;

import com.easybank.fbp.common.dto.*;
import com.esaybank.fbp.accounts.client.LoansClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@OpenAPIDefinition(info = @Info(
		title = "ACCOUNTS-SERVICE",
		description = "EASY-BANK ACCOUNTS MICROSERVICE",
		version = "V1",
		summary = "we will get the information about easy-bank accounts"
))
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {AppProperties.class})
@SpringBootApplication
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
