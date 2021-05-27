package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "item"}) // toString 제외
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // 다 대 일 N : 1
    @ManyToOne
    private User user; // Hibernate에서 알아서 user_id를 찾아감

    // 다 대 일 N : 1
    @ManyToOne
    private Item item;

}
