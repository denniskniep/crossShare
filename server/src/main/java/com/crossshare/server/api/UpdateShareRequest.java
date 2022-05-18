package com.crossshare.server.api;

import com.crossshare.server.entities.models.SharedObject;
import com.crossshare.server.entities.models.SharedObjectType;

public class UpdateShareRequest {
    private String secret;

    private SharedObjectType type;

    private SharedObject sharedObject;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public SharedObjectType getType() {
        return type;
    }

    public void setType(SharedObjectType type) {
        this.type = type;
    }

    public SharedObject getSharedObject() {
        return sharedObject;
    }

    public void setSharedObject(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }
}
