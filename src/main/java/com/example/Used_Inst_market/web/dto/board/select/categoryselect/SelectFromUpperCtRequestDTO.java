package com.example.Used_Inst_market.web.dto.board.select.categoryselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromUpperCtRequestDTO {
    private Long upperCategoryNo;

    @Builder
    public SelectFromUpperCtRequestDTO(Long upperCategoryNo) {

        this.upperCategoryNo = upperCategoryNo;
    }
}
