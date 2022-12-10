package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class UpperCategoryVO {
    private Long upperCategoryNo;
    private String name;

    public UpperCategoryVO(UpperCategory upperCategory) {
        this.upperCategoryNo = upperCategory.getUpperCategoryNo();
        this.name = upperCategory.getName();
    }

    public static UpperCategoryVO from(UpperCategory upperCategory) {
        return new UpperCategoryVO(upperCategory);
    }
}
