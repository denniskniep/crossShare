package com.crossshare.server.api;

import com.crossshare.server.entities.models.Share;
import com.crossshare.server.services.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ShareController {

    private ShareService shareService;

    public ShareController(ShareService shareService){
        this.shareService = shareService;
    }

    @ResponseBody
    @PostMapping(value = "/api/share/find")
    public ResponseEntity<Optional<Share>> findShare(@RequestBody FindShareRequest findShareRequest) {
        Optional<Share> share = shareService.find(findShareRequest.getSecret());
        if(share.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(share);
    }

    @ResponseBody
    @PostMapping(value = "/api/share/create")
    public ResponseEntity<Share> createShare() {
        Share share = shareService.createShare();
        return ResponseEntity.status(HttpStatus.CREATED).body(share);
    }

    @ResponseBody
    @PostMapping(value = "/api/share/update")
    public ResponseEntity updateShare(@RequestBody UpdateShareRequest updateShareRequest) {
        shareService.updateSharedObject(updateShareRequest.getSecret(), updateShareRequest.getType(), updateShareRequest.getSharedObject());
        return ResponseEntity.ok().build();
    }
}
