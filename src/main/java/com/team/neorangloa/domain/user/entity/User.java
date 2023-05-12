package com.team.neorangloa.domain.user.entity;

import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Entity
public class User {
    @Id
    @Column(name = "USER_ID")
    private Long id;

    private String email;

    private String nickname;

    @Column(name = "IS_REMOVED")
    private boolean removed = false;
}
