package com.sipl.springhelloworld.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    
    // hello world api basepoint
    @GetMapping()
    public ResponseEntity<String> helloWorld() {
        ResponseEntity<String> response = new ResponseEntity<String>("Hello World! Atharva Malji here.", null, HttpStatus.OK);
        return response;
    }
}
