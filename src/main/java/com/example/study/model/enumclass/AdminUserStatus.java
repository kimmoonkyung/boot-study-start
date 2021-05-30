package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserStatus {

    REGISTERED(0,"관리자 등록상태","관리자 등록상태"),
    UNREGISTERED(1,"관리자 해지","관리자 해지상태");

    private Integer id;
    private String title;
    private String description;

}
