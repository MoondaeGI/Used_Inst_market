package com.example.Used_Inst_market.web.dto.board.select.localselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromUpperLoRequestDTO {
    Long upperLocalNo;

    @Builder
    public SelectFromUpperLoRequestDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
