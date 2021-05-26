package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @Table(name = "User") -> 테이블명과 동일하다면 선언하지 않아도 됨.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account") // 테이블의 컬럼명과 동일하다면 선언하지 않아도 자동 매칭됨
    private String account;

    @Column(name = "email")
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime updateAt;

    @Column
    private String updatedBy;

}
