package com.sipl.springhelloworld.responses;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseApiResponse {
    private boolean status;
    private HttpStatus statusCode;
    private String error;
    private String message;
    private Map<String,Object> responseData;
}