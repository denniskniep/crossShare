package com.crossshare.demoClient.services;

import com.crossshare.demoClient.services.RedirectShareObject;

public class UpdateShareRequest {
    private String secret;

    private String type;

    private RedirectShareObject sharedObject;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RedirectShareObject getSharedObject() {
        return sharedObject;
    }

    public void setSharedObject(RedirectShareObject sharedObject) {
        this.sharedObject = sharedObject;
    }
}
