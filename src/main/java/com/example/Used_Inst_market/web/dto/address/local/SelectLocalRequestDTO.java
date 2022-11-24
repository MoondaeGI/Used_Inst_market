package com.example.Used_Inst_market.web.dto.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectLocalRequestDTO {
    private Long localNo;

    @Builder
    public SelectLocalRequestDTO(Long localNo) {
        this.localNo = localNo;
    }
}
