package com.example.Used_Inst_market.web.dto.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalUpdateRequestDTO {
    private Long localNo;
    private String name;

    @Builder
    public LocalUpdateRequestDTO(Long localNo, String name) {
        this.localNo = localNo;
        this.name = name;
    }
}
