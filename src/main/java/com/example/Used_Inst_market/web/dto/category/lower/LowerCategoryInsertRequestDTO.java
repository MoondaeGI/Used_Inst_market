package com.example.Used_Inst_market.web.dto.category.lower;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryInsertRequestDTO {
    private UpperCategory upperCategory;
    private String name;

    @Builder
    public LowerCategoryInsertRequestDTO(UpperCategory upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }

    public LowerCategory toEntity() {
        return LowerCategory.builder()
                .upperCategory(upperCategory)
                .name(name)
                .build();
    }
}
