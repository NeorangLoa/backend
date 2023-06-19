package com.team.neorangloa.domain.user.service;

import com.team.neorangloa.domain.user.dto.LoginRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.util.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final HttpSession httpSession;
    private final UserService userService;

    public String login(HttpServletRequest request, long id) {
        // 기존 세션 가져오기
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 기존 세션이 존재하면 세션을 만료시킴
            session.invalidate();
        }

        // 새로운 세션 생성 및 세션 ID 저장
        HttpSession newSession = request.getSession(true);
        String newSessionId = newSession.getId();
        newSession.setAttribute(newSessionId, id);
        return newSessionId;
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

        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = httpSession.getAttribute(attributeName);
            System.out.println(attributeName);
            System.out.println(attributeValue);
        }
        return (Long) httpSession.getAttribute(sessionId);
    }

    public boolean isUserLogin(String sessionId) {
        return getLoginUserId(sessionId) != null;
    }
    public User getLoginUser(String sessionId) {
        return userService.findUserById(getLoginUserId(sessionId));
    }
}
