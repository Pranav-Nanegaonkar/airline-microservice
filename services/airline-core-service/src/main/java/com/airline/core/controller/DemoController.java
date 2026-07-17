package com.airline.core.controller;

import com.airline.commonlib.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping
    public ResponseEntity<ApiResponse> demo() {
        return ResponseEntity.ok().body(new ApiResponse("AIRLINE-CORE-SERVICE"));
    }
}
