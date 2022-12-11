package com.example.Used_Inst_market.web.dto.local.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalDeleteRequestDTO {
    private Long upperLocalNo;

    @Builder
    public UpperLocalDeleteRequestDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
