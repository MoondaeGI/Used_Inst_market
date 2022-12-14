package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class UpperCategoryVO {
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;
    @ApiParam(name = "상위 카테고리 이름", required = true,
            value = "name", example = "example")
    private String name;

    public UpperCategoryVO(UpperCategory upperCategory) {
        this.upperCategoryNo = upperCategory.getUpperCategoryNo();
        this.name = upperCategory.getName();
    }

    public static UpperCategoryVO from(UpperCategory upperCategory) {
        return new UpperCategoryVO(upperCategory);
    }
}
