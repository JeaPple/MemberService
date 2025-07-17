package com.practice.member.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // 회원가입 : DTO -> Entity 변환을 위한 생성자 추가
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


    //==비밀번호, 이름 수정 메서드==//
    public void updateUserInfo(String password, String name) {
        this.password = password;
        this.name = name;
    }



}
