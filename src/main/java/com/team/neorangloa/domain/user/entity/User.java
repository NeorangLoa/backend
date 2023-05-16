package com.team.neorangloa.domain.user.entity;

import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "USERS")
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "IS_REMOVED" ,nullable = false)
    private boolean removed;
}
