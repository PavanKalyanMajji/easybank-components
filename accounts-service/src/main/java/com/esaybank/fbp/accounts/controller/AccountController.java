package com.esaybank.fbp.accounts.controller;

import com.easybank.fbp.common.config.function.BaseEndPoints;
import com.easybank.fbp.common.dto.AppProperties;
import com.esaybank.fbp.accounts.entity.Accounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public interface AccountController {
    @GetMapping("/ping")
    ResponseEntity<String> ping();

    @GetMapping("/app-description")
    ResponseEntity<AppProperties> appDescription();

    @PostMapping
    ResponseEntity<Accounts> createAccount(@RequestBody Accounts accounts);

    @GetMapping("/account-detail/{accountNumber}")
    ResponseEntity<Accounts> getAccountDetailsByAccountNumber(@PathVariable String accountNumber );

//    Supplier<ResponseEntity<String>> getLoansServiceStatus();
}
