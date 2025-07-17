package com.practice.member.user.repository;

import com.practice.member.user.domain.WithdrawUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawUserRepository extends JpaRepository<WithdrawUser, Long> {
}
