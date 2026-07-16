package com.airline.locationservice.controller;

import com.airline.commonlib.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/")
    public ApiResponse hello() {

        return new ApiResponse("Hello World");
    }
}
