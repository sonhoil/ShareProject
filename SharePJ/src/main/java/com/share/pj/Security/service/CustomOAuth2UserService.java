package com.share.pj.Security.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.share.pj.Auth.dto.UserEntity;
import com.share.pj.Auth.service.AuthService;
import com.share.pj.Security.UserPrincipal;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    	System.out.println("loadUser");
        // OAuth2 서버로부터 사용자 정보를 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 인증 서버 응답에서 사용자 속성 가져오기
        Map<String, Object> userAttributes = new HashMap<>(oAuth2User.getAttributes());

        UserEntity userEntity = new UserEntity();
        // access token과 인증 방식(kakao, naver 등) 설정
        userEntity.setAccess_token(userRequest.getAccessToken().getTokenValue());
        userEntity.setLoginFlag(userRequest.getClientRegistration().getRegistrationId());

        // AuthService를 사용해 현재 로직으로 사용자 데이터 로드
        Object userOrAuthInfo;
        try {
            // AuthService를 사용해 현재 로직으로 사용자 데이터 로드
            userOrAuthInfo = authService.login(userEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // 적절한 예외 처리를 수행하고, 메소드를 종료
            return null;
        }

        // AuthService가 UserEntity를 반환하면 UserPrincipal로 변환, 그렇지 않으면 상황 처리 (예: 새 사용자 등록)
        if (userOrAuthInfo instanceof UserEntity) {
            // 사용자 속성을 추가하여 사용자를 생성
            return UserPrincipal.create((UserEntity) userOrAuthInfo, userAttributes);
        } else {
            // 사용자가 등록되지 않았을 때의 상황 처리
        	return UserPrincipal.create((UserEntity) userOrAuthInfo, userAttributes);
        }
    }
}