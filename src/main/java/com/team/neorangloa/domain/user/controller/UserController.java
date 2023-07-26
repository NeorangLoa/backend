package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.UserInfoResponse;
import com.team.neorangloa.domain.user.dto.SignupRequest;
import com.team.neorangloa.domain.user.dto.UpdateNicknameRequest;
import com.team.neorangloa.domain.user.dto.UpdatePasswordRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.annotation.LoginUser;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<ResultResponse> signup(
            @RequestBody @Valid SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_SIGNUP_SUCCESS));
    }

    @GetMapping("/user-info/{userId}")
    public ResponseEntity<ResultResponse> getLoginUserInfo(@PathVariable Long userId) {
        UserInfoResponse userInfoResponse = userService.findUserDtoById(userId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_USER_SUCCESS, userInfoResponse));
    }

    @PutMapping("/nickname")
    @LoginRequired
    public ResponseEntity<ResultResponse> updateNickname(@LoginUser User loginUser,
                                                         @RequestBody @Valid UpdateNicknameRequest updateNicknameRequest) {
        Long logInUserId = loginUser.getId();
        userService.updateNickname(logInUserId, updateNicknameRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_NICKNAME_SUCCESS, UserInfoResponse.of(loginUser)));
    }

    @PutMapping("/password")
    @LoginRequired
    public ResponseEntity<ResultResponse> updatePassword(@LoginUser User loginUser,
                                                         @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        Long logInUserId = loginUser.getId();
        if (!userService.isValidPassword(logInUserId,updatePasswordRequest)) {
            throw new InvalidPasswordException();
        }
        userService.updatePassword(logInUserId, updatePasswordRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_PASSWORD_SUCCESS));
    }


    @GetMapping("/charactersInfo/{characterName}")
    public ResponseEntity<String> getCharactersInfo(@RequestHeader("API-Key") String apiKey,
                                                    @PathVariable String characterName,
                                                    @RequestParam(required = false, defaultValue = "") String filter) {
        String apiUrl = "https://developer-lostark.game.onstove.com/armories/characters/" + characterName +"/"+ filter;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, apiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}