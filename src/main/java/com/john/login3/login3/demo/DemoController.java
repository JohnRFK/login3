package com.john.login3.login3.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    @PostMapping(value = "demo")
    public String welcome(@RequestBody String entity) {
        //TODO: process POST request
        
        return "welcome from secure webpoint";
    }
    

}
