package com.douglima.bankaccountservice.dto;

import com.douglima.bankaccountservice.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class AccountRequest {
    private Integer number;
    @NotNull
    private Integer agency;
    @NotNull
    private AccountType accountType;
}
