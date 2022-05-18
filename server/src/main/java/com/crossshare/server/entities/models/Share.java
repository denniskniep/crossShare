package com.crossshare.server.entities.models;

import javax.persistence.*;

@Entity
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String secret;

    private SharedObjectType type;

    @Column(columnDefinition="clob")
    private String sharedObject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSharedObject() {
        return sharedObject;
    }

    public void setSharedObject(String sharedObject) {
        this.sharedObject = sharedObject;
    }
}