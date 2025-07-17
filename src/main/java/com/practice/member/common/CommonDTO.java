package com.practice.member.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDTO {
    private String status_message;
    private int status_code;
    private Object result;
}
