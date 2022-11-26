package com.example.Used_Inst_market.web.dto.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalSelectRequestDTO {
    private Long localNo;

    @Builder
    public LocalSelectRequestDTO(Long localNo) {
        this.localNo = localNo;
    }
}
