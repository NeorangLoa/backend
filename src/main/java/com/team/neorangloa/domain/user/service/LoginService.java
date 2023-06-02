package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.LoginRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.util.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final HttpSession httpSession;
    private final UserService userService;

    public void login(HttpServletRequest request, long id) {
        HttpSession session = request.getSession(false);
        session.setAttribute(session.getId(), id);
    }
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false); // 현재 세션 가져오기 (세션이 없는 경우 null 반환)
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
    }
    public boolean isValidUser(LoginRequest loginRequest) {
        User user = userService.findUserByEmail(loginRequest.getEmail());
        return PasswordEncryptionUtil.isSamePassword(loginRequest.getPassword(), user.getPassword());
    }

    public Long getLoginUserId(String sessionId) {
        return (Long) httpSession.getAttribute(sessionId);
    }

    public boolean isUserLogin(String sessionId) {
        return getLoginUserId(sessionId) != null;
    }
    public User getLoginUser(String sessionId) {
        return userService.findUserById(getLoginUserId(sessionId));
    }
}
