package com.team.neorangloa.domain.user.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class DuplicatedEmailException extends BusinessException {
    public DuplicatedEmailException(){
        super(ErrorCode.USER_EMAIL_DUPLICATED);
    }
}