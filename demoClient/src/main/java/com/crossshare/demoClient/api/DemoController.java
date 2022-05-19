package com.crossshare.demoClient.api;

import com.crossshare.demoClient.services.CrossShareClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private CrossShareClient crossShareClient;

    public DemoController(CrossShareClient crossShareClient){
        this.crossShareClient = crossShareClient;
    }

    @ResponseBody
    @PostMapping(value = "/api/share")
    public ResponseEntity share(@RequestBody RedirectShareRequest request) {
        crossShareClient.share(request.getSecret(), request.getUrl());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
