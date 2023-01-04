package com.example.Used_Inst_market.model.dto.category.brand;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandSelectFromLowerDTO {
    private Long lowerCategoryNo;

    @Builder
    public BrandSelectFromLowerDTO(Long lowerCategoryNo) {
        this.lowerCategoryNo = lowerCategoryNo;
    }
}
