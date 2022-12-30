package com.example.Used_Inst_market.web.dto.category.lower;

import lombok.Getter;

@Getter
public class LowerCategorySelectFromUpperDTO {
    private Long upperCategoryNo;

    public LowerCategorySelectFromUpperDTO(Long upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }
}
