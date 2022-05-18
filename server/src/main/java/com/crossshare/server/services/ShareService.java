package com.crossshare.server.services;

import com.crossshare.server.entities.models.Share;
import com.crossshare.server.entities.models.SharedObject;
import com.crossshare.server.entities.models.SharedObjectType;
import com.crossshare.server.entities.repositories.ShareRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class ShareService {

    private Logger logger = LoggerFactory.getLogger(ShareService.class);

    private ShareRepository shareRepository;

    public ShareService(ShareRepository shareRepository){
        this.shareRepository = shareRepository;
    }

    public Share createShare(){
        String secret = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        Share share = new Share();
        share.setSecret(secret);
        shareRepository.save(share);
        logger.info("Created new share id {}", share.getId());
        return share;
    }

    public void updateSharedObject(String secret, SharedObjectType type, SharedObject sharedObject){
        String sharedObjectAsJson;
        try {
            sharedObjectAsJson = new ObjectMapper().writeValueAsString(sharedObject);
        } catch (IOException e) {
            throw new RuntimeException("Error during serialisation of sharedObject", e);
        }
        Optional<Share> foundShare = find(secret);
        if(foundShare.isEmpty()){
            throw new RuntimeException("Share not found");
        }
        foundShare.get().setType(type);
        foundShare.get().setSharedObject(sharedObjectAsJson);
        logger.info("Updated share {} with: \n{}", foundShare.get().getId(), foundShare.get().getSharedObject());
        shareRepository.save(foundShare.get());
    }

    public Optional<Share> find(String secret){
        List<Share> foundShares = shareRepository.findBySecret(secret);
        if(foundShares.size() == 0 || foundShares.size() > 1){
            return Optional.empty();
        }
        return Optional.ofNullable(foundShares.get(0));
    }
}
