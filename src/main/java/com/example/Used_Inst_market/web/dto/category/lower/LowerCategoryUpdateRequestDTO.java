package com.example.Used_Inst_market.web.dto.category.lower;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryUpdateRequestDTO {
    @ApiParam(name = "하위 카테고리 번호", required = true,
            value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;
    @ApiParam(name = "상위 카테고리", required = true)
    private UpperCategory upperCategory;
    @ApiParam(name = "상위 카테고리 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public LowerCategoryUpdateRequestDTO(Long lowerCategoryNo,
                                         UpperCategory upperCategory, String name) {
        this.lowerCategoryNo = lowerCategoryNo;
        this.upperCategory = upperCategory;
        this.name = name;
    }
}
