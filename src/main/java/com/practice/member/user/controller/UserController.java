package com.practice.member.user.controller;

import com.practice.member.common.CommonDTO;
import com.practice.member.common.CommonErrorDTO;
import com.practice.member.user.dto.UserCreateReqDTO;
import com.practice.member.user.dto.UserDetailResDTO;
import com.practice.member.user.dto.UserUpdateReqDTO;
import com.practice.member.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/create")
    public ResponseEntity<?> createUser(
            @RequestBody UserCreateReqDTO reqDTO
    ) {

        // 비밀번호길이 검증 로직
        if (reqDTO.getPassword().length() < 5) {
            return new ResponseEntity<>(new CommonErrorDTO(
                    "비밀번호가 짧습니다.",
                    HttpStatus.BAD_REQUEST.value()
            ), HttpStatus.BAD_REQUEST);
        }

        userService.join(reqDTO);

        return new ResponseEntity<>(new CommonDTO(
                "회원가입이 완료됐습니다.",
                HttpStatus.CREATED.value(), null
        ), HttpStatus.CREATED);
    }

    // 회원 상세 조회
    @GetMapping("/{userId}")
    public ResponseEntity<CommonDTO> getUser(
            @PathVariable Long userId
    ) {
        UserDetailResDTO resDTO = userService.getUser(userId);
        return new ResponseEntity<>(new CommonDTO(
                "유저 조회 성공",
                HttpStatus.OK.value(),
                resDTO
        ), HttpStatus.OK);
    }

    // 회원 목록 조회
    @GetMapping("/list")
    public ResponseEntity<CommonDTO> getUserList() {
        List<UserDetailResDTO> userList = userService.getUserList();
        return new ResponseEntity<>(new CommonDTO(
                "유저 목록 조회 성공",
                HttpStatus.OK.value(),
                userList
        ), HttpStatus.OK);
    }


    // 회원 정보 수정
    @PutMapping("/update")
    public ResponseEntity<CommonDTO> updateUser(UserUpdateReqDTO reqDTO) {
        UserDetailResDTO resDTO = userService.updateUser(reqDTO);
        return new ResponseEntity<>(new CommonDTO(
                "회원정보 수정 완료",
                HttpStatus.OK.value(),
                resDTO
        ), HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonDTO> deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new CommonDTO(
                "회원이 탈퇴되었습니다.",
                HttpStatus.OK.value(), null
        ), HttpStatus.OK);
    }






}
