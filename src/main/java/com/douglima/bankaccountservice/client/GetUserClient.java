package com.douglima.bankaccountservice.client;

import com.douglima.bankaccountservice.dto.client.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetUserClient {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    private final String USER_API = "http://localhost:8082/api/v1/users/";

    public UserInfo execute(UUID id) {
        httpHeaders.set(httpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserInfo> response = restTemplate.exchange(
                USER_API + id,
                HttpMethod.GET,
                entity,
                UserInfo.class
        );
        if(response.getStatusCode().isError()) throw new RestClientException(response.getStatusCode().toString());
        return response.getBody();
    }

}
