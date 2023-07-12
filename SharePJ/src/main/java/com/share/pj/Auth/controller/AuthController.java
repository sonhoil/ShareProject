package com.share.pj.Auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.AuthEntity;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.service.AuthService;
import com.share.pj.Common.Api.kakaoLogin;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authservice;
	
   
    @ResponseBody
    @PostMapping("/api/login")
    public Object login(@RequestBody AuthEntity authEntity) throws JsonMappingException, JsonProcessingException {
    	System.out.println(authEntity.getAccess_token());
    	System.out.println(authEntity.getLoginFlag());
    	AuthEntity authInfo = kakaoLogin.getKakaoUserInfo(authEntity.getAccess_token());
    	authInfo.setLoginFlag(authEntity.getLoginFlag());
    	System.out.println(authInfo);
    	Object result = authservice.login(authEntity);
    	System.out.println("result ==>"+result);
    	return result;
    }
    
    @ResponseBody
    @PostMapping("/api/confirmPhoneNumber")
    public String confirmPhoneNumber(@RequestBody UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
    	String result = authservice.confirmPhoneNumber(userEntity);
    	return result;
    }
    
    @ResponseBody
    @PostMapping("/api/registUser")
    public UserEntity registUser(@RequestBody UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
    	System.out.println("userEntity ===>"+userEntity);
    	UserEntity result = authservice.registUser(userEntity);
    	return result;
    }
  
}