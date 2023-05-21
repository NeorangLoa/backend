package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.SignupRequestDto;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.mapper.UserMapper;
import com.team.neorangloa.domain.user.repository.UserRepository;
import com.team.neorangloa.domain.user.exception.DuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void signup(SignupRequestDto signupRequestDto) {
        validateDuplication(signupRequestDto);

        User user = userMapper.toEntity(signupRequestDto);
        user.encryptPassword();
        userRepository.save(user);
    }
    private void validateDuplication(SignupRequestDto signupRequestDto) {
        validateEmail(signupRequestDto.getEmail());
    }
    private void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicatedEmailException();
        }
    }
}