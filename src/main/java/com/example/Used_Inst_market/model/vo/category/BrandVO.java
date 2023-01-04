package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class BrandVO {
    @ApiParam(name = "브랜드 번호", required = true, value = "brandNo", example = "1")
    private Long brandNo;
    @ApiParam(name = "브랜드 이름", required = true, value = "name", example = "example")
    private String name;

    public BrandVO(Brand brand) {
        this.brandNo = brand.getBrandNo();
        this.name = brand.getName();
    }

    public static BrandVO from(Brand brand) {
        return new BrandVO(brand);
    }
}
