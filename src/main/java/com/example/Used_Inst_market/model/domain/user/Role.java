package com.example.Used_Inst_market.model.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자 계정"),
    USER("ROLE_USER", "유저 계정");

    private final String key;
    private final String title;
}
