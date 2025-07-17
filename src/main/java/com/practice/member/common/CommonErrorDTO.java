package com.practice.member.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonErrorDTO {
    private String status_message;
    private int status_code;
}
