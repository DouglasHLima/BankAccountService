package com.douglima.bankaccountservice.mapper;

import com.douglima.bankaccountservice.dto.AccountRequest;
import com.douglima.bankaccountservice.dto.AccountResponse;
import com.douglima.bankaccountservice.dto.client.UserInfo;
import com.douglima.bankaccountservice.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.ManyToOne;

@Mapper(
        componentModel = "spring"
)
public interface AccountMapper {

    Account toEntity(AccountRequest accountRequest);

    @Mapping(target = "user", source="userInfo")
    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "creation", source = "account.creation")
    @Mapping(target = "update", source = "account.update")
    AccountResponse toResponse(Account account, UserInfo userInfo);
}
