package com.share.pj.Auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.share.pj.Auth.dto.AuthEntity;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.mapper.AuthMapper;
import com.share.pj.Auth.repository.UserRepository;
import com.share.pj.Common.Api.kakaoLogin;

@Service
public class AuthService {
	
	@Autowired
	private AuthMapper authMapper;
	@Autowired
	private UserRepository userRepository;
	public int login(AuthEntity authEntity) throws JsonMappingException, JsonProcessingException {
		AuthEntity authInfo = kakaoLogin.getKakaoUserInfo(authEntity.getAccess_token());
    	authInfo.setLoginFlag(authEntity.getLoginFlag());
    	
    	authMapper.getUserInfo(authInfo);
    	switch (authEntity.getLoginFlag()) {
    	case "kakao":
    		List<UserEntity> user = userRepository.findByKakao(authEntity.getId());
    		break;
    	case "naver":
    		break;
    	case "google":
    		break;
    	}
		return 0;
	}

}
