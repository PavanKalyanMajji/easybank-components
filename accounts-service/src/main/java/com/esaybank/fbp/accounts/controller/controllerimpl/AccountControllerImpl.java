package com.esaybank.fbp.accounts.controller.controllerimpl;

import com.easybank.fbp.common.config.functionimpl.BaseEndPointsImpl;
import com.easybank.fbp.common.dto.AppProperties;
import com.esaybank.fbp.accounts.client.LoansClient;
import com.esaybank.fbp.accounts.controller.AccountController;
import com.esaybank.fbp.accounts.entity.Accounts;
import com.esaybank.fbp.accounts.repository.AccountsRepository;
import feign.form.ContentType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountControllerImpl extends BaseEndPointsImpl implements AccountController {
    private final AccountsRepository accountsRepository;
    private final AppProperties appProperties;
    private final LoansClient loansClient;

    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Accounts Service is Running ...  :)");
    }

    @Override
    public ResponseEntity<AppProperties> appDescription() {
        return ResponseEntity.ok(appProperties);
    }

    @Override
    public ResponseEntity<Accounts> createAccount(Accounts accounts) {
        return new ResponseEntity<>(accountsRepository.save(accounts), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Accounts> getAccountDetailsByAccountNumber(String accountNumber) {
        Accounts accountDetails=accountsRepository.findByAccountNumber(accountNumber).orElse(new Accounts());
        return new ResponseEntity<>(
                accountDetails,
                accountDetails.getAccountNumber()!=null ? HttpStatus.FOUND : HttpStatus.NOT_FOUND);
    }

    /**
     * Below api give loans service status.
     * URL: - http://localhost:8012/base-endpoint/getServiceStatus
     * @return Supplier<ResponseEntity<String>>
     */
    @Override
    @Tag(name = "Get Loans-Service Server status",
    description = "Api gives the Loans-Service server running details")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Loans-Service is Running",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))))
    public Supplier<ResponseEntity<String>> getServiceStatus() {
        return () -> loansClient.ping();
    }
}
