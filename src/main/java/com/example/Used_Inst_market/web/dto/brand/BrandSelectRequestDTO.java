package com.example.Used_Inst_market.web.dto.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandSelectRequestDTO {
    Long brandNo;

    @Builder
    public BrandSelectRequestDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}
