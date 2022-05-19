package com.crossshare.demoClient.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrossShareClient {

    private String crossShareServerUrl;

    public CrossShareClient(@Value("${cross-share.server.url}") String crossShareServerUrl){
        this.crossShareServerUrl = crossShareServerUrl;
    }

    public void share(String secret, String url){
        RestTemplate restTemplate = new RestTemplate();

        UpdateShareRequest updateShareRequest = new UpdateShareRequest();
        updateShareRequest.setSecret(secret);
        updateShareRequest.setType("REDIRECT");
        updateShareRequest.setSharedObject(new RedirectShareObject(url));

        HttpEntity<UpdateShareRequest> request = new HttpEntity<>(updateShareRequest);
        restTemplate.postForObject(crossShareServerUrl + "/api/share/update", request, Object.class);
    }
}
