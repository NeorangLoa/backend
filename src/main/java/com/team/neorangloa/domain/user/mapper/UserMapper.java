package com.team.neorangloa.domain.user.mapper;


import com.team.neorangloa.domain.user.dto.SignupRequest;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(SignupRequest dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .removed(false)
                .build();
    }
}