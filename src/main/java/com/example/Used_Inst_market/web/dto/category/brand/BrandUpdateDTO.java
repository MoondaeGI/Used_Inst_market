package com.example.Used_Inst_market.web.dto.category.brand;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandUpdateDTO {
    @ApiParam(name = "브랜드 번호", required = true,
            value = "brandNo", example = "1")
    private Long brandNo;
    @ApiParam(name = "하위 카테고리", required = true)
    private LowerCategory lowerCategory;
    @ApiParam(name = "브랜드 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public BrandUpdateDTO(Long brandNo, LowerCategory lowerCategory, String name) {
        this.brandNo = brandNo;
        this.lowerCategory = lowerCategory;
        this.name = name;
    }
}
