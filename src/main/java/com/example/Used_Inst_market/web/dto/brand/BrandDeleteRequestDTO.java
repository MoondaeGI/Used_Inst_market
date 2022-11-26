package com.example.Used_Inst_market.web.dto.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandDeleteRequestDTO {
    private Long brandNo;

    @Builder
    public BrandDeleteRequestDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}
