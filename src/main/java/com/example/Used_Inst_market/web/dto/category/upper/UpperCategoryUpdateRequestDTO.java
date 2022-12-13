package com.example.Used_Inst_market.web.dto.category.upper;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperCategoryUpdateRequestDTO {
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;
    @ApiParam(name = "상위 카테고리 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public UpperCategoryUpdateRequestDTO(Long upperCategoryNo, String name) {
        this.upperCategoryNo = upperCategoryNo;
        this.name = name;
    }
}
