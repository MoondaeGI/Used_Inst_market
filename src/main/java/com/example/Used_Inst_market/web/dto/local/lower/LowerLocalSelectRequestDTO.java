package com.example.Used_Inst_market.web.dto.local.lower;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalSelectRequestDTO {
    private Long lowerLocalNo;

    @Builder
    public LowerLocalSelectRequestDTO(Long lowerLocalNo) {
        this.lowerLocalNo = lowerLocalNo;
    }
}
