package com.example.Used_Inst_market.web.dto.category.lower;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryUpdateRequestDTO {
    private Long lowerCategoryNo;
    private UpperCategory upperCategory;
    private String name;

    @Builder
    public LowerCategoryUpdateRequestDTO(Long lowerCategoryNo,
                                         UpperCategory upperCategory, String name) {
        this.lowerCategoryNo = lowerCategoryNo;
        this.upperCategory = upperCategory;
        this.name = name;
    }
}
