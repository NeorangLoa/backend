package com.team.neorangloa.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // user
    USER_SIGNUP_SUCCESS("U001", "사용자 등록 성공"),
    USER_USERNAME_DUPLICATED("U002", "회원 아이디 중복"),
    USER_USERNAME_NOT_DUPLICATED("U003", "회원 아이디 중복되지 않음"),
    USER_LOGIN_SUCCESS("U004", "회원 로그인 성공"),
    USER_LOGOUT_SUCCESS("U005", "회원 로그아웃 성공"),
    GET_LOGIN_USER_SUCCESS("U006", "로그인 되어있는 회원 조회 성공"),

    // post
    POST_CREATE_SUCCESS("S001", "게시물 생성 성공"),
    POST_GET_SUCCESS("S002", "게시물 조회 성공"),
    POST_PAGING_GET_SUCCESS("S003", "게시물 페이징 조회 성공"),
    USER_POST_PAGING_GET_SUCCESS("S004", "유저별 게시물 페이징 조회 성공"),
    POST_UPDATE_SUCCESS("S005", "게시물 수정 성공"),

    POST_DELETE_SUCCESS("S006", "게시물 삭제 성공"),

    // comment
    REGISTER_COMMENT_SUCCESS("C001", "댓글 등록 성공"),
    COMMENT_FIND_SUCCESS("C002", "댓글 찾기 성공"),
    COMMENT_UPDATE_SUCCESS("C003", "댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS("C004", "댓글 삭제 성공")
    ;

    private final String code;
    private final String message;
}
