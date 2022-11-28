package com.example.Used_Inst_market.web.vo.category;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandVO {
    private Long brandNo;
    private String name;
    private LowerCategory lowerCategory;

    @Builder
    public BrandVO(Brand brand) {
        this.brandNo = brand.getBrandNo();
        this.name = brand.getName();
        this.lowerCategory = brand.getLowerCategory();
    }
}
