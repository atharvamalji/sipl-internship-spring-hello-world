package com.sipl.springhelloworld.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sipl.springhelloworld.dtos.UserMasterDto;
import com.sipl.springhelloworld.entities.UserMaster;
import com.sipl.springhelloworld.mappers.UserMasterMapper;
import com.sipl.springhelloworld.repositories.UserMasterRepository;
import com.sipl.springhelloworld.responses.UserMasterApiResponse;
import com.sipl.springhelloworld.services.UserMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMasterServiceImpl implements UserMasterService {
    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private UserMasterMapper userMasterMapper;
    
    public UserMasterApiResponse createNewUser(UserMasterDto userMasterDto) {
        log.info("Inside createNewUser()");
        UserMasterApiResponse response = new UserMasterApiResponse();

        Optional<UserMaster> userMasterExists = userMasterRepository
                .findByUsername(userMasterDto.getUsername());

        if (!userMasterExists.isPresent()) {
            try {
                String encodedPassword = new BCryptPasswordEncoder().encode(userMasterDto.getPassword());
                userMasterDto.setPassword(encodedPassword);
                UserMaster userMaster = userMasterMapper.mapUserMasterDtoToUserMaster(userMasterDto);
                UserMaster newUserMaster = userMasterRepository.save(userMaster);
                UserMasterDto result = userMasterMapper.mapUserMasterToUserMasterDto(newUserMaster);

                JSONObject responseData = new JSONObject();
                JSONObject createdUserMasterJsonObject = new JSONObject(result);
                responseData.put("generatedUser", createdUserMasterJsonObject);

                response.setStatus(true);
                response.setStatusCode(HttpStatus.OK);
                response.setMessage("User created successfully");
                response.setResponseData(responseData.toMap());

                return response;
            } catch (Exception e) {
                response.setStatus(false);
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setError(e.toString());
                response.setMessage("Error occured while adding the user.");
                return response;
            }

        }

        response.setStatus(false);
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setMessage("User already exists");
        return response;
    }

    public UserMasterApiResponse getAllUsers() {
        log.info("Inside getAllUsers()");
        UserMasterApiResponse response = new UserMasterApiResponse();
        try {
            List<UserMasterDto> listUserMasterDto;
            List<UserMaster> listUserMaster = new ArrayList<UserMaster>();
            Iterable<UserMaster> userMasterIterable = userMasterRepository.findAll();
            userMasterIterable.forEach(listUserMaster::add);
            listUserMasterDto = userMasterMapper.mapListUserMasterToListUserMasterDto(listUserMaster);

            JSONObject responseData = new JSONObject();
            responseData.put("users", listUserMasterDto);

            response.setStatus(true);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Users fetched successfully");
            response.setResponseData(responseData.toMap());

            return response;
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Something went wrong while fetching users");
            return response;
        }
    }
}
