package com.team.neorangloa.domain.user.entity;

import com.team.neorangloa.global.entity.BaseTimeEntity;
import com.team.neorangloa.global.util.PasswordEncryptionUtil;
import lombok.Builder;
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
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "IS_REMOVED" ,nullable = false)
    private boolean removed;

    @Builder
    public User(String email, String password, String nickname, boolean removed) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.removed = removed;
    }

    public void encryptPassword() {
        this.password = PasswordEncryptionUtil.encrypt(password);
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}