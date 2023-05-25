package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.LoginRequestDto;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.global.util.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final HttpSession httpSession;
    private final UserService userService;
    public static final String USER_ID = "USER_ID";

    public void login(long id) {
        httpSession.setAttribute(USER_ID, id);
    }
    public void logout(){
        httpSession.removeAttribute(USER_ID);
    }
    public boolean isValidUser(LoginRequestDto loginRequestDto) {
        User user = userService.findUserByEmail(loginRequestDto.getEmail());
        return PasswordEncryptionUtil.isSamePassword(loginRequestDto.getPassword(), user.getPassword());
    }

    public Long getLoginUserId() {
        return (Long) httpSession.getAttribute(USER_ID);
    }

    public boolean isUserLogin() {
        return getLoginUserId() != null;
    }

    public User getLoginUser() {
        return userService.findUserById(getLoginUserId());
    }
}
