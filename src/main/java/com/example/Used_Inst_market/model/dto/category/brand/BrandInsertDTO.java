package com.example.Used_Inst_market.model.dto.category.brand;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BrandInsertDTO {
    @ApiParam(name = "하위 카테고리 번호", required = true, value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;
    @ApiParam(name = "브랜드 이름", required = true, value = "brandNo", example = "example")
    private String name;
}
