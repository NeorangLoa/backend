package com.team.neorangloa.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),

    // 예시
    // User 도메인
    INVALID_PASSWORD(400, "U001", "잘못된 비밀번호"),
    USER_NOT_FOUND_ERROR(400, "U002", "사용자를 찾을 수 없음"),
    UNAUTHORIZED_ACCESS_ERROR(403, "U003", "승인되지 않은 접근"),
    USER_EMAIL_DUPLICATED(409, "U004", "회원 이메일 중복"),

    // Post
    POST_DUPLICATION_ERROR(409, "S001", "게시물의 이름이 중복됨"),
    POST_NOT_FOUND_ERROR(400, "S002", "게시물을 찾을 수 없음"),
    RAID_NOT_FOUND_ERROR(409, "S003", "레이드 정보를 찾을 수 없음"),

    // Comment
    COMMENT_NOT_FOUND_ERROR(400, "C001", "댓글을 찾을 수 없음");

    private final int status;
    private final String code;
    private final String message;
}
