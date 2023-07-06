package com.share.pj.Auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.share.pj.Auth.dto.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	public List<UserEntity> findByKakao(String kakao);
}
