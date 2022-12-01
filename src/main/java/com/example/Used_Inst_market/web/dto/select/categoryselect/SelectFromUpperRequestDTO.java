package com.example.Used_Inst_market.web.dto.select.categoryselect;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromUpperRequestDTO {
    private Long upperCategoryNo;

    @Builder
    public SelectFromUpperRequestDTO(Long upperCategoryNo) {

        this.upperCategoryNo = upperCategoryNo;
    }
}
