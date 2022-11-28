package com.example.Used_Inst_market.web.dto.category.brand;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandUpdateRequestDTO {
    private Long brandNo;
    private LowerCategory lowerCategory;
    private String name;

    @Builder
    public BrandUpdateRequestDTO(Long brandNo, LowerCategory lowerCategory, String name) {
        this.brandNo = brandNo;
        this.lowerCategory = lowerCategory;
        this.name = name;
    }
}
