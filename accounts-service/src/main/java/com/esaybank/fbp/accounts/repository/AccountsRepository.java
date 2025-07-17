package com.esaybank.fbp.accounts.repository;

import com.esaybank.fbp.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Accounts, UUID> {
    Optional<Accounts> findByAccountNumber(String accountNumber);
}
