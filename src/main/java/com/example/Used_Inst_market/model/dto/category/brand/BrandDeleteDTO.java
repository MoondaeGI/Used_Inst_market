package com.example.Used_Inst_market.model.dto.category.brand;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandDeleteDTO {
    @ApiParam(name = "브랜드 번호", required = true,
            value = "brandNo", example = "1")
    private Long brandNo;

    @Builder
    public BrandDeleteDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}
