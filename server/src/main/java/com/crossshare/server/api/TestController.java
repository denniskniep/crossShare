package com.crossshare.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @ResponseBody
    @GetMapping(value = "/api/test")
    public ResponseEntity<Object> test() {
        return  ResponseEntity.ok("TEST");
    }
}
