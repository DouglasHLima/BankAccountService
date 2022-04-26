package com.douglima.bankaccountservice.service;

import com.douglima.bankaccountservice.dto.AccountRequest;
import com.douglima.bankaccountservice.dto.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountService {
    Page<AccountResponse> getAll(Pageable page);
    AccountResponse create(UUID usuarioId, AccountRequest accountRequest);

    AccountResponse getById(UUID id);

    AccountResponse update(UUID id, AccountRequest accountRequest);

    void deleteById(UUID id);
}
