package com.share.pj.Auth.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.repository.UserRepository;
import com.share.pj.Common.Api.kakaoLogin;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	public Object login(UserEntity UserEntity) throws JsonMappingException, JsonProcessingException {
	    UserEntity authInfo = kakaoLogin.getKakaoUserInfo(UserEntity.getAccess_token());
	    authInfo.setLoginFlag(UserEntity.getLoginFlag());

	    Optional<UserEntity> user;
	    switch (authInfo.getLoginFlag()) {
	    case "kakao":
	        user = Optional.ofNullable(userRepository.findByKakao(authInfo.getId()));
	        break;
	    case "naver":
	    case "google":
	        user = Optional.empty();  
	        break;
	    default:
	        user = Optional.empty();
	    }

	    return user.orElseGet(() -> {
	        authInfo.setStatus("regist");
	        return authInfo;
	    });
	}
	
	public String confirmPhoneNumber(UserEntity userEntity) {
		UserEntity user = userRepository.findByphone(userEntity.getPhone());
		return (user == null) ? "using" : "exist";
	}	

	public UserEntity registUser(UserEntity userEntity) {
		UserEntity user = userRepository.findByphone(userEntity.getPhone());
		if(user != null) return null;
		
		userEntity.setUid(UUID.randomUUID().toString());
		switch (userEntity.getLoginFlag()) {
    	case "kakao":
    		userEntity.setKakao(userEntity.getId());
    		break;
    	case "naver":
    	case "google":
    		break;
    	}
		
		return userRepository.save(userEntity);
	}	
}
