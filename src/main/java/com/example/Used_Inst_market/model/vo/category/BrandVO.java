package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class BrandVO {
    private Long brandNo;
    private String name;
    private LowerCategory lowerCategory;

    public BrandVO(Brand brand) {
        this.brandNo = brand.getBrandNo();
        this.name = brand.getName();
        this.lowerCategory = brand.getLowerCategory();
    }

    public static BrandVO from(Brand brand) {
        return new BrandVO(brand);
    }
}
