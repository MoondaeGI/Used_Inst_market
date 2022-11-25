package com.example.Used_Inst_market.web.dto.category.upper;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertUpperCategoryRequestDTO {
    private String name;

    @Builder
    public InsertUpperCategoryRequestDTO(String name) {
        this.name = name;
    }

    public UpperCategory toEntity() {
        return UpperCategory.builder()
                .name(name)
                .build();
    }
}
