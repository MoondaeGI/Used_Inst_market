package com.example.Used_Inst_market.web.dto.category.categoryselect;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectedAsBrandRequestDTO {
    private Brand brand;

    @Builder
    public SelectedAsBrandRequestDTO(Brand brand) {
        this.brand = brand;
    }
}