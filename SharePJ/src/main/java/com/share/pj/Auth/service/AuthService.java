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
	public String login(AuthEntity authEntity) throws JsonMappingException, JsonProcessingException {
		AuthEntity authInfo = kakaoLogin.getKakaoUserInfo(authEntity.getAccess_token());
    	authInfo.setLoginFlag(authEntity.getLoginFlag());
    	List<UserEntity> user = null;
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
    	if(user.size() < 1) {
			/*
			 * UserEntity newUser = new UserEntity();
			 * newUser.setUid(UUID.randomUUID().toString());
			 * newUser.setId(authInfo.getId());
			 * 
			 * 
			 * newUser.setKakao(authInfo.getId()); userRepository.save(newUser); user =
			 * userRepository.findByKakao(authInfo.getId());
			 */
    		return "regist";
		}
    	
		return "success";
	}

}
