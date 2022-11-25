package com.example.Used_Inst_market.web.vo.category;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperCategoryInfoVO {
    private Long upperCategoryNo;
    private String name;

    @Builder
    public UpperCategoryInfoVO(UpperCategory upperCategory) {
        this.upperCategoryNo = upperCategory.getUpperCategoryNo();
        this.name = upperCategory.getName();
    }
}
