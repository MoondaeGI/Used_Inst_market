package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LowerCategoryVO {
    @ApiParam(name = "하위 카테고리 번호", required = true,
            value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;
    @ApiParam(name = "상위 카테고리", required = true)
    private UpperCategory upperCategory;
    @ApiParam(name = "하위 카테고리 이름", required = true,
            value = "name", example = "example")
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
