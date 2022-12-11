package com.example.Used_Inst_market.web.dto.local.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalSelectRequestDTO {
    private Long upperLocalNo;

    @Builder
    public UpperLocalSelectRequestDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
