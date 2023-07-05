package com.share.pj.Auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.share.pj.Auth.dto.AuthEntity;

@RestController
public class AuthController {

    @ResponseBody
    @PostMapping("/api/login")
    public String login(@RequestBody AuthEntity authEntity) {
    	System.out.println(authEntity.getId());
    	
    	return "";
    }
    @ResponseBody
    @PostMapping("/api/loginCheck")
    public String loginCheck(@RequestBody AuthEntity authEntity) {
    	System.out.println(authEntity.getId());
    	
    	return "";
    }
  
}