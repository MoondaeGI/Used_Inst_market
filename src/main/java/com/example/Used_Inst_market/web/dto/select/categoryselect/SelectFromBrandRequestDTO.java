package com.example.Used_Inst_market.web.dto.select.categoryselect;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromBrandRequestDTO {
    private Long brandNo;

    @Builder
    public SelectFromBrandRequestDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}