package com.example.Used_Inst_market.model.dto.local.lower;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LowerLocalSelectFromUpperDTO {
    private Long upperLocalNo;

    @Builder
    public LowerLocalSelectFromUpperDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
