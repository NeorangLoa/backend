package com.team.neorangloa.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotEmpty
    private String currentPassword;
    @NotEmpty
    private String newPassword;
}
