package com.example.Used_Inst_market.web.dto.brand;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertBrandRequestDTO {
    private LowerCategory lowerCategory;
    private String name;

    @Builder
    public InsertBrandRequestDTO(Brand brand) {
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
