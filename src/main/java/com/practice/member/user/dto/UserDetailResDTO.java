package com.practice.member.user.dto;

import com.practice.member.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResDTO {
    private String email;
    private String name;

    //==객체 생성 메서드==//
    public static UserDetailResDTO from(User user) {
        return new UserDetailResDTO(
                user.getEmail(),
                user.getName()
        );
    }

}
