package com.douglima.bankaccountservice.controller;

import com.douglima.bankaccountservice.dto.AccountRequest;
import com.douglima.bankaccountservice.dto.AccountResponse;
import com.douglima.bankaccountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/api/v1/accounts",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<?> getAll(Pageable page) {
        return ResponseEntity.ok(accountService.getAll(page));

    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountResponse> getById(
            @PathVariable("accountId") UUID id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AccountResponse> create(
            @PathVariable("userId") UUID userId,
            @Valid @RequestBody AccountRequest accountRequest
    ) {
        val response = accountService.create(userId, accountRequest);
        return ResponseEntity
                .created(linkTo(methodOn(AccountController.class).getById(response.getId()))
                        .withSelfRel().toUri())
                .body(response);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AccountResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AccountRequest accountRequest
    ) {
        AccountResponse response = accountService.update(id, accountRequest);
        return ResponseEntity
                .created(linkTo(methodOn(AccountController.class).getById(response.getId()))
                        .withSelfRel().toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
