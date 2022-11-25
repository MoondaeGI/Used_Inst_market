package com.example.Used_Inst_market.web.dto.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectBrandRequestDTO {
    Long brandNo;

    @Builder
    public SelectBrandRequestDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}
