package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    CARD(0, "카드 결제", "카드를 사용하여 결제"),
    CASH(1, "현금 결제", "무통장 입금")
    ;

    private Integer id;
    private String title;
    private String description;
}
