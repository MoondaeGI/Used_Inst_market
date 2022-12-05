package com.example.Used_Inst_market.web.dto.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalDeleteRequestDTO {
    private Long localNo;

    @Builder
    public LocalDeleteRequestDTO(Long localNo) {
        this.localNo = localNo;
    }
}
