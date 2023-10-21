package com.sipl.springhelloworld.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.springhelloworld.controllers.UserMasterController;
import com.sipl.springhelloworld.dtos.UserMasterDto;
import com.sipl.springhelloworld.responses.UserMasterApiResponse;
import com.sipl.springhelloworld.services.impl.UserMasterServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserMasterControllerImpl implements UserMasterController {
    @Autowired
    UserMasterServiceImpl userMasterService;

    @PostMapping()
    public ResponseEntity<UserMasterApiResponse> createNewUser(@RequestBody UserMasterDto userMasterDto) {
        UserMasterApiResponse fetchedResponse = userMasterService.createNewUser(userMasterDto);
        ResponseEntity<UserMasterApiResponse> response = new ResponseEntity<UserMasterApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }

    @GetMapping()
    public ResponseEntity<UserMasterApiResponse> getAllUsers() {
        UserMasterApiResponse fetchedResponse = userMasterService.getAllUsers ();
        ResponseEntity<UserMasterApiResponse> response = new ResponseEntity<UserMasterApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }
}
