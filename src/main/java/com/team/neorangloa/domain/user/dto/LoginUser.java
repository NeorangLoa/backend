package com.team.neorangloa.domain.user.dto;

import com.team.neorangloa.domain.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginUser implements Serializable {
    private String email;
    private String password;

    public LoginUser(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
