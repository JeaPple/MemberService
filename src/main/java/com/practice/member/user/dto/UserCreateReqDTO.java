package com.practice.member.user.dto;

import com.practice.member.user.domain.User;
import lombok.Getter;

@Getter
public class UserCreateReqDTO {
    private String email;
    private String password;
    private String name;

    public User toEntity() {
        return new User(this.email, this.password, this.name);
    }
}
