package com.share.pj.Auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.service.AuthService;
import com.share.pj.Common.Api.kakaoLogin;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
    // Login endpoint
    @PostMapping("/api/login")
    public Object login(@RequestBody UserEntity UserEntity) throws JsonMappingException, JsonProcessingException {
    	UserEntity authInfo = kakaoLogin.getKakaoUserInfo(UserEntity.getAccess_token());
    	authInfo.setLoginFlag(UserEntity.getLoginFlag());
    	return authService.login(UserEntity);
    }
    
    // Confirm phone number endpoint
    @PostMapping("/api/confirmPhoneNumber")
    public String confirmPhoneNumber(@RequestBody UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
    	return authService.confirmPhoneNumber(userEntity);
    }
    
    // Register user endpoint
    @PostMapping("/api/registUser")
    public UserEntity registUser(@RequestBody UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
    	return authService.registUser(userEntity);
    }
  
}