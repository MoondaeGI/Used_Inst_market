package com.example.Used_Inst_market.web.vo.category;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryVO {
    private Long lowerCategoryNo;
    private UpperCategory upperCategory;
    private String name;

    @Builder
    public LowerCategoryVO(LowerCategory lowerCategory) {
        this.lowerCategoryNo = lowerCategory.getLowerCategoryNo();
        this.upperCategory = lowerCategory.getUpperCategory();
        this.name = lowerCategory.getName();
    }
}
