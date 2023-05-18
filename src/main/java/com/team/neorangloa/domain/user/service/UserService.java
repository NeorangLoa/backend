package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.SignupRequestDto;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.mapper.UserMapper;
import com.team.neorangloa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void signup(SignupRequestDto signupRequestDto) {

        User user = userMapper.toEntity(signupRequestDto);
        user.encryptPassword();
        userRepository.save(user);
    }

}