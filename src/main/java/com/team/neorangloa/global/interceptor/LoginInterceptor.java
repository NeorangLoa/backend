package com.team.neorangloa.global.interceptor;

import com.team.neorangloa.domain.user.exception.UnAuthorizedAccessException;
import com.team.neorangloa.domain.user.service.LoginService;
import com.team.neorangloa.global.annotation.LoginRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnAuthorizedAccessException {
        String sessionId = extractSessionId(request); // 클라이언트의 세션 ID 추출

        if (isLoginRequiredMethod(handler)) {
            System.out.println("wow1");
        }
        if (!loginService.isUserLogin(sessionId)) {
            System.out.println(sessionId);
        }
        if (isLoginRequiredMethod(handler) && !loginService.isUserLogin(sessionId)) {
            throw new UnAuthorizedAccessException();
        }
        return true;
    }
    private boolean isLoginRequiredMethod(Object handler) {
        return handler instanceof HandlerMethod
                && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class);
    }
    private String extractSessionId(HttpServletRequest request) {
        return request.getSession().getId(); // 세션 ID 추출 (여기서는 기존의 세션 ID 사용)
    }
}
