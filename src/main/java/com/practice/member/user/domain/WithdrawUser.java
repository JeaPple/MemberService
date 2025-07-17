package com.practice.member.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    public WithdrawUser(User user) {
        this.user = user;
    }

    public static WithdrawUser of(User user) {
        return new WithdrawUser(user);
    }
}
