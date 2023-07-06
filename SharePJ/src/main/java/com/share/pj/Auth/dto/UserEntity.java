package com.share.pj.Auth.dto;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="User")
public class UserEntity {
	private String uid;
	private String id;
	private String name;
	private String phone;
	private String kakao;
}
