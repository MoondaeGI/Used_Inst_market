package com.example.Used_Inst_market.web.dto.category.brand;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandInsertRequestDTO {
    private LowerCategory lowerCategory;
    private String name;

    @Builder
    public BrandInsertRequestDTO(Brand brand) {
        this.lowerCategory = brand.getLowerCategory();
        this.name = brand.getName();
    }

    public Brand toEntity() {
        return Brand.builder()
                .lowerCategory(lowerCategory)
                .name(name)
                .build();
    }
}
