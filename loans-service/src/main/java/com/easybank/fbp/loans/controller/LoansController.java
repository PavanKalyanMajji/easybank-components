package com.easybank.fbp.loans.controller;

import com.easybank.fbp.loans.dto.AppProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface LoansController {
    @GetMapping("/ping")
    ResponseEntity<String> ping();

    @GetMapping("/app-description")
    ResponseEntity<AppProperties> getAppDescription();
}
