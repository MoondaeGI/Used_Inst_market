package com.example.Used_Inst_market.web.dto.local.lower;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalDeleteRequestDTO {
    private Long lowerLocalNo;

    @Builder
    public LowerLocalDeleteRequestDTO(Long lowerLocalNo) {
        this.lowerLocalNo = lowerLocalNo;
    }
}
