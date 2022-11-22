package com.example.Used_Inst_market.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "게스트 계정"),
    USER("ROLE_USER", "유저 계정");

    private final String key;
    private final String title;
}
