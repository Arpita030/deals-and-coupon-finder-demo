package com.dealsfinder.cashbackservice.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UserClientService {

    private final RestTemplate restTemplate;

    @Value("${USER_SERVICE_URL:http://USER-SERVICE/users/email}")
    private String userServiceUrl;

    public UserClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidUser(String email) {
        try {
            restTemplate.getForObject(userServiceUrl + "/" + email, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
