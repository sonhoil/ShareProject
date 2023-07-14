package com.share.pj.Security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.share.pj.Auth.dto.UserEntity;

public class UserPrincipal extends DefaultOAuth2User {

    private UserEntity userEntity;

    public UserPrincipal(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, UserEntity userEntity) {
        super(authorities, attributes, nameAttributeKey);
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }
    
    public static UserPrincipal create(UserEntity userEntity, Map<String, Object> attributes) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(authorities, attributes, "id", userEntity);
    }

}
