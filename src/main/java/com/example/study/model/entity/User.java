package com.example.study.model.entity;


import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "orderGroupList")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
// @Table(name = "User") -> 테이블명과 동일하다면 선언하지 않아도 됨.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column // 테이블의 컬럼명과 동일하다면 선언하지 않아도 자동 매칭됨
    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // User 1 : N OrderGroup
    // 1대N 관계는 페치타입을 걸어줘야 한다.
    // 1대N 이기 때문에 리스트 타입으로 받아와야한다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
