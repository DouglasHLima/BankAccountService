package com.douglima.bankaccountservice.service.impl;

import com.douglima.bankaccountservice.client.GetUserClient;
import com.douglima.bankaccountservice.dto.AccountRequest;
import com.douglima.bankaccountservice.dto.AccountResponse;
import com.douglima.bankaccountservice.dto.client.UserInfo;
import com.douglima.bankaccountservice.exceptions.AccountNotFoundException;
import com.douglima.bankaccountservice.mapper.AccountMapper;
import com.douglima.bankaccountservice.model.Account;
import com.douglima.bankaccountservice.repository.AccountRepository;
import com.douglima.bankaccountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final GetUserClient getUserClient;

    @Override
    public Page<AccountResponse> getAll(Pageable page) {
        return accountRepository.findAll(page).map( account ->
                accountMapper.toResponse(account,getUserClient.execute(account.getUser()))
        );
    }

    @Override
    public AccountResponse create(UUID userId, AccountRequest accountRequest) {
        UserInfo user = getUserClient.execute(userId);
        Account account = accountMapper.toEntity(accountRequest);
        account.setUser(user.getId());
        Account saved = accountRepository.save(account);
        return accountMapper.toResponse(saved,getUserClient.execute(saved.getUser()));
    }

    @Override
    public AccountResponse getById(UUID id) {
        Account found = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with Id " + id + " not exists")
        );
        return accountMapper.toResponse(found,getUserClient.execute(found.getUser()));
    }

    @Override
    public AccountResponse update(UUID id,AccountRequest accountRequest) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(String.format(
                        "The Account with id %d does't exists",
                        id))
        );
        BeanUtils.copyProperties(account, accountRequest);
        Account saved = accountRepository.save(account);
        return accountMapper.toResponse(saved, getUserClient.execute(saved.getUser()));
    }

    @Override
    public void deleteById(UUID id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new AccountNotFoundException(String.format("Account with id: %d doesn't exists", id));
        }
    }


}
