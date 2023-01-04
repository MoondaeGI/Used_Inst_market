package com.example.Used_Inst_market.model.dto.category.upper;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperCategoryDeleteDTO {
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;

    @Builder
    public UpperCategoryDeleteDTO(Long upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }
}
