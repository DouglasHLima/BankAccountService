package com.douglima.bankaccountservice.dto;

import com.douglima.bankaccountservice.dto.client.UserInfo;
import com.douglima.bankaccountservice.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AccountResponse {
    private UUID id;
    private Integer number;
    private Integer agency;
    private LocalDateTime creation;
    private LocalDateTime update;
    private BigDecimal balance;
    private AccountType accountType;
    private UserInfo user;
}
