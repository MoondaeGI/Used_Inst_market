package com.example.Used_Inst_market.web.dto.category.brand;

import lombok.Getter;

@Getter
public class BrandSelectFromLowerDTO {
    private Long lowerCategoryNo;

    public BrandSelectFromLowerDTO(Long lowerCategoryNo) {
        this.lowerCategoryNo = lowerCategoryNo;
    }
}
