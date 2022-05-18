package com.crossshare.server.api;

import com.crossshare.server.entities.models.SharedObject;

public class FindShareRequest {
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
