package com.example.Used_Inst_market.web.dto.category.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperCategorySelectRequestDTO {
    private Long upperCategoryNo;

    @Builder
    public UpperCategorySelectRequestDTO(Long upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }
}