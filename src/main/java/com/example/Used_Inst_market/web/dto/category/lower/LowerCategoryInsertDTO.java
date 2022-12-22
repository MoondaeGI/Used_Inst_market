package com.example.Used_Inst_market.web.dto.category.lower;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryInsertDTO {
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;
    @ApiParam(name = "하위 카테고리 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public LowerCategoryInsertDTO(Long upperCategoryNo, String name) {
        this.upperCategoryNo = upperCategoryNo;
        this.name = name;
    }
}
