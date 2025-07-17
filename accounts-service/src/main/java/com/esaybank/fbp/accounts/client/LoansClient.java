package com.esaybank.fbp.accounts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("loans-service/api")
public interface LoansClient {
    @GetMapping("/ping")
    ResponseEntity<String> ping();
}
