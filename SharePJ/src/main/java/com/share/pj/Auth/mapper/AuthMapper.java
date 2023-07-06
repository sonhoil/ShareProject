package com.share.pj.Auth.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.share.pj.Auth.dto.AuthEntity;


public interface AuthMapper {

	public int getUserInfo(AuthEntity authEntity) throws DataAccessException;
}
