package com.example.Used_Inst_market.web.vo.brand;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandInfoVO {
    private Long brandNo;
    private String name;
    private LowerCategory lowerCategory;

    @Builder
    public BrandInfoVO(Brand brand) {
        this.brandNo = brand.getBrandNo();
        this.name = brand.getName();
        this.lowerCategory = brand.getLowerCategory();
    }
}
