package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.*;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.mapper.UserMapper;
import com.team.neorangloa.domain.user.repository.UserRepository;
import com.team.neorangloa.domain.user.exception.DuplicatedEmailException;
import com.team.neorangloa.domain.user.exception.UserNotFoundException;
import com.team.neorangloa.global.util.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void signup(SignupRequest signupRequest) {
        validateDuplication(signupRequest);

        User user = userMapper.toEntity(signupRequest);
        user.encryptPassword();
        userRepository.save(user);
    }
    private void validateDuplication(SignupRequest signupRequest) {
        validateEmail(signupRequest.getEmail());
    }
    private void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicatedEmailException();
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }
  
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public boolean isValidPassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        User user = findUserById(userId);
        return PasswordEncryptionUtil.isSamePassword(updatePasswordRequest.getCurrentPassword(), user.getPassword());
    }

    @Transactional
    public void updateNickname(Long userId, UpdateNicknameRequest updateNicknameRequest) {
        User logInUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        logInUser.updateNickname(updateNicknameRequest.getNickname());
    }

    @Transactional
    public void updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        User logInUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        logInUser.updatePassword(updatePasswordRequest.getNewPassword());
        logInUser.encryptPassword();
    }
}