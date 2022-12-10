package com.example.Used_Inst_market.web.dto.category.upper;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperCategoryInsertRequestDTO {
    private String name;

    @Builder
    public UpperCategoryInsertRequestDTO(String name) {
        this.name = name;
    }

    public UpperCategory toEntity() {
        return UpperCategory.builder()
                .name(name)
                .build();
    }
}
