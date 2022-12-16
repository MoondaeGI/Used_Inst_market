package com.example.Used_Inst_market.web.dto.category.brand;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandSelectDTO {
    @ApiParam(name = "브랜드 번호", required = true,
            value = "brandNo", example = "1")
    Long brandNo;

    @Builder
    public BrandSelectDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}