package com.crossshare.demoClient.services;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrossShareClient {

    public void share(String secret, String url){
        RestTemplate restTemplate = new RestTemplate();

        UpdateShareRequest updateShareRequest = new UpdateShareRequest();
        updateShareRequest.setSecret(secret);
        updateShareRequest.setType("REDIRECT");
        updateShareRequest.setSharedObject(new RedirectShareObject(url));

        HttpEntity<UpdateShareRequest> request = new HttpEntity<>(updateShareRequest);
        restTemplate.postForObject("http://localhost:8080/api/share/update", request, Object.class);
    }
}
