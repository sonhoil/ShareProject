package com.share.pj.Auth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.AuthEntity;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.repository.UserRepository;
import com.share.pj.Common.Api.kakaoLogin;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	public Object login(AuthEntity authEntity) throws JsonMappingException, JsonProcessingException {
		AuthEntity authInfo = kakaoLogin.getKakaoUserInfo(authEntity.getAccess_token());
    	authInfo.setLoginFlag(authEntity.getLoginFlag());
    	UserEntity user = null;
    	String LoginFlag = authInfo.getLoginFlag();
    	switch (LoginFlag) {
    	case "kakao":
    		user = userRepository.findByKakao(authInfo.getId());
    		break;
    	case "naver":
    		break;
    	case "google":
    		break;
    	}
    	if(user == null) {
			/*
			 * UserEntity newUser = new UserEntity();
			 * newUser.setUid(UUID.randomUUID().toString());
			 * newUser.setId(authInfo.getId());
			 * 
			 * 
			 * newUser.setKakao(authInfo.getId()); userRepository.save(newUser); user =
			 * userRepository.findByKakao(authInfo.getId());
			 */
    		authInfo.setStatus("regist");
    		return authInfo;
		}else {
			return user;
		}
    	
		
	}
	
	public String confirmPhoneNumber(UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
		System.out.println(userEntity.getPhone());
		UserEntity user = userRepository.findByphone(userEntity.getPhone());
		if(user == null) return "using"; else return "exist";
		
	}	
	public UserEntity registUser(UserEntity userEntity) throws JsonMappingException, JsonProcessingException {
		UserEntity user = userRepository.findByphone(userEntity.getPhone());
		if(user != null) return null;
		
		userEntity.setUid(UUID.randomUUID().toString());
		String LoginFlag = userEntity.getLoginFlag();
		switch (LoginFlag) {
    	case "kakao":
    		userEntity.setKakao(userEntity.getId());
    		break;
    	case "naver":
    		break;
    	case "google":
    		break;
    	}
		
		UserEntity result = userRepository.save(userEntity);
		return result;
	}	

}
