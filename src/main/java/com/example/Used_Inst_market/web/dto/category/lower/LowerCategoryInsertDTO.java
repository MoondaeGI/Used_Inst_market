package com.example.Used_Inst_market.web.dto.category.lower;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryInsertDTO {
    @ApiParam(name = "상위 카테고리", required = true)
    private UpperCategory upperCategory;
    @ApiParam(name = "하위 카테고리 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public LowerCategoryInsertDTO(UpperCategory upperCategory, String name) {
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
