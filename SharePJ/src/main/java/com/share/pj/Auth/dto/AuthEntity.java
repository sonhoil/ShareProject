package com.share.pj.Auth.dto;

import lombok.Data;

@Data
public class AuthEntity {
	private String id;
	private String loginFlag;
	private String access_token;
}
