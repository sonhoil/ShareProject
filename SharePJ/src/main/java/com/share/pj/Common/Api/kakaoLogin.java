package com.share.pj.Common.Api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.share.pj.Auth.dto.AuthEntity;
import com.share.pj.Auth.dto.UserEntity;

public class kakaoLogin {
	public static UserEntity getKakaoUserInfo(String accessToken) throws JsonMappingException, JsonProcessingException {
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + accessToken);

	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

	    ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, String.class);

	    ObjectMapper mapper = new ObjectMapper();
	    UserEntity authInfo = mapper.readValue(response.getBody(), UserEntity.class);
	    return authInfo;
	}
}
