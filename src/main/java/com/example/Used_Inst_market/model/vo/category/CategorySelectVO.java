package com.example.Used_Inst_market.model.vo.category;

import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class CategorySelectVO {
    @ApiParam(name = "상위 카테고리", readOnly = true,
            value = "upperCategory", example = "{upperCategoryNo: '1', name: 'example'}")
    private UpperCategoryVO upperCategory;
    @ApiParam(name = "하위 카테고리", readOnly = true,
            value = "lowerCategory", example = "{lowerCategoryNo: '1', name: 'example'}")
    private LowerCategoryVO lowerCategory;
    @ApiParam(name = "브랜드", readOnly = true,
            value = "brand", example = "{brandNo: '1', name: 'example'}")
    private BrandVO brand;

    private CategorySelectVO(CategorySelect categorySelect) {
        this.upperCategory = UpperCategoryVO.from(categorySelect.getUpperCategory());
        this.lowerCategory = LowerCategoryVO.from(categorySelect.getLowerCategory());
        this.brand = BrandVO.from(categorySelect.getBrand());
    }

    public static CategorySelectVO from(CategorySelect categorySelect) {
        return new CategorySelectVO(categorySelect);
    }
}
