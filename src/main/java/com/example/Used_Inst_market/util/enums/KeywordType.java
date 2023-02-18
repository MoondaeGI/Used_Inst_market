package com.example.Used_Inst_market.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum KeywordType {
    TITLE("제목"),
    CONTENT("내용");

    private final String status;
}
