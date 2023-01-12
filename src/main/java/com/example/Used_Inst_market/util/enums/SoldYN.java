package com.example.Used_Inst_market.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SoldYN {
    SOLD_OUT("판매 완료"),
    SALE("판매 중");

    private final String status;
}
