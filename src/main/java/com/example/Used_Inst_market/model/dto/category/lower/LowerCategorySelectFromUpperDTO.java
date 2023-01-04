package com.example.Used_Inst_market.model.dto.category.lower;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LowerCategorySelectFromUpperDTO {
    private Long upperCategoryNo;

    @Builder
    public LowerCategorySelectFromUpperDTO(Long upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }
}
