package com.example.KAFKA_TEST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserVo {
    private String name;
    private String password;
    private String option;

}
