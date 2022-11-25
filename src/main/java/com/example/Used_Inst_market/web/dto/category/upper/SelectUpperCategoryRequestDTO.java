package com.example.Used_Inst_market.web.dto.category.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectUpperCategoryRequestDTO {
    private Long upperCategoryNo;

    @Builder
    public SelectUpperCategoryRequestDTO(Long upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }
}
