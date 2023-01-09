package com.example.Used_Inst_market.model.dto.category.upper;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpperCategoryInsertDTO {
    @ApiParam(name = "상위 카테고리 이름", required = true, value = "name", example = "example")
    private String name;

    public UpperCategory toEntity() {
        return UpperCategory.builder()
                .name(name)
                .build();
    }
}
