package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LowerCategoryVO {
    private Long lowerCategoryNo;
    private UpperCategory upperCategory;
    private String name;

    public LowerCategoryVO(LowerCategory lowerCategory) {
        this.lowerCategoryNo = lowerCategory.getLowerCategoryNo();
        this.upperCategory = lowerCategory.getUpperCategory();
        this.name = lowerCategory.getName();
    }

    public static LowerCategoryVO from(LowerCategory lowerCategory) {
        return new LowerCategoryVO(lowerCategory);
    }
}
