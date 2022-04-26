package com.douglima.bankaccountservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserInfo {
    private UUID id;
    private String cpf;
    private String name;
    private LocalDateTime creation;
    private LocalDateTime update;
}
