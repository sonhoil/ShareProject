package com.share.pj.Auth.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor // Added this annotation
@Entity(name="User")
public class UserEntity extends AuthEntity{
	@Id
	private String uid;
	private String id;
	private String name;
	private String phone;
	private String kakao;
}