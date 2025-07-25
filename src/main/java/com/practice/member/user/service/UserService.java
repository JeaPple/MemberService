package com.practice.member.user.service;

import com.practice.member.exception.BusinessException;
import com.practice.member.exception.ExceptionCode;
import com.practice.member.user.domain.User;
import com.practice.member.user.domain.WithdrawUser;
import com.practice.member.user.dto.UserCreateReqDTO;
import com.practice.member.user.dto.UserDetailResDTO;
import com.practice.member.user.dto.UserUpdateReqDTO;
import com.practice.member.user.repository.UserRepository;
import com.practice.member.user.repository.WithdrawUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WithdrawUserRepository withdrawUserRepository;

    public void join(UserCreateReqDTO dto) {
        Optional<User> findEmail = userRepository.findByEmail(dto.getEmail());
        if (findEmail.isPresent()) {
            throw new BusinessException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        User user = dto.toEntity();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDetailResDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.USER_NOT_FOUND));

        return UserDetailResDTO.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserDetailResDTO> getUserList() {
        return userRepository.findAll().stream().map(UserDetailResDTO::from)
                .collect(Collectors.toList());
    }

    public UserDetailResDTO updateUser(UserUpdateReqDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BusinessException(ExceptionCode.USER_NOT_FOUND));

        user.updateUserInfo(dto.getPassword(), dto.getName());
        return UserDetailResDTO.from(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.USER_NOT_FOUND));

        withdrawUserRepository.save(WithdrawUser.of(user));
    }



}
