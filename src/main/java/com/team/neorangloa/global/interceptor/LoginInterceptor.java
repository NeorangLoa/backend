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
        if (isLoginRequiredMethod(handler) && !loginService.isUserLogin()) {
            throw new UnAuthorizedAccessException();
        }
        return true;
    }
    private boolean isLoginRequiredMethod(Object handler) {
        return handler instanceof HandlerMethod
                && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class);
    }
}
