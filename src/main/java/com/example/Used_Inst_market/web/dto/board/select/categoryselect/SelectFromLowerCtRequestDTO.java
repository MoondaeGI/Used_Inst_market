package com.example.Used_Inst_market.web.dto.board.select.categoryselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromLowerCtRequestDTO {
    private Long lowerCategoryNo;

    @Builder
    public SelectFromLowerCtRequestDTO(Long lowerCategoryNo) {

        this.lowerCategoryNo = lowerCategoryNo;
    }
}
