package com.easybank.fbp.loans.controller.controllerimpl;

import com.easybank.fbp.loans.controller.LoansController;
import com.easybank.fbp.loans.dto.AppProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoansControllerImpl implements LoansController {
    private final AppProperties appProperties;

    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Loans Service is Running.... :)");
    }

    @Override
    public ResponseEntity<AppProperties> getAppDescription() {
        return ResponseEntity.ok(appProperties);
    }
}
