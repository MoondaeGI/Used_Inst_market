package com.example.Used_Inst_market.web.dto.select.categoryselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromLowerRequestDTO {
    private Long lowerCategoryNo;

    @Builder
    public SelectFromLowerRequestDTO(Long lowerCategoryNo) {

        this.lowerCategoryNo = lowerCategoryNo;
    }
}
