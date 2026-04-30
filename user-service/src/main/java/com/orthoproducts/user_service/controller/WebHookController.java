package com.orthoproducts.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @PostMapping("/product")
    public ResponseEntity<?> recieveWebHook(@RequestBody Map<String, Object> payload){
        System.out.println("WebHook Recieved ---> " + payload);
        return ResponseEntity.ok("Received");
    }
}