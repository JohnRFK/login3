package com.john.login3.login3.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



//Exponer las rutas con los endpoints
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "login")   
    public String login(){
        return "login para endpoint público";
    }

    @PostMapping("path")
    public String register(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}
