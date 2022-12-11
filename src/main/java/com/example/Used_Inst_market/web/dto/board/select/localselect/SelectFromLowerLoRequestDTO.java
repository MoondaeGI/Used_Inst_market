package com.example.Used_Inst_market.web.dto.board.select.localselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromLowerLoRequestDTO {
    private Long lowerLocalNo;

    @Builder
    public SelectFromLowerLoRequestDTO(Long lowerLocalNo) {
        this.lowerLocalNo = lowerLocalNo;
    }
}
