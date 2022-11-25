package com.example.Used_Inst_market.web.dto.category.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUpperCategoryRequestDTO {
    private Long upperCategoryNo;
    private String name;

    @Builder
    public UpdateUpperCategoryRequestDTO(Long upperCategoryNo, String name) {
        this.upperCategoryNo = upperCategoryNo;
        this.name = name;
    }
}
