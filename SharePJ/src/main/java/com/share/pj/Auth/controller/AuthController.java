package com.share.pj.Auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.AuthEntity;
import com.share.pj.Common.Api.kakaoLogin;

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
    public String loginCheck(@RequestBody AuthEntity authEntity) throws JsonMappingException, JsonProcessingException {
    	System.out.println(authEntity.getAccess_token());
    	System.out.println(authEntity.getLoginFlag());
    	AuthEntity authInfo = kakaoLogin.getKakaoUserInfo(authEntity.getAccess_token());
    	authInfo.setLoginFlag(authEntity.getLoginFlag());
    	System.out.println(authInfo);
    	return "";
    }
  
}