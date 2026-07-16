package com.airline.userservice.controller;

import com.airline.commonlib.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {


    @GetMapping
    public ApiResponse demo() {
        return new ApiResponse("USER-SERVICE");
    }


}
