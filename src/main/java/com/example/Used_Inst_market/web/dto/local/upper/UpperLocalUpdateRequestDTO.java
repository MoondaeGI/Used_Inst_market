package com.example.Used_Inst_market.web.dto.local.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalUpdateRequestDTO {
    private Long upperLocalNo;
    private String name;

    @Builder
    public UpperLocalUpdateRequestDTO(Long upperLocalNo, String name) {
        this.upperLocalNo = upperLocalNo;
        this.name = name;
    }
}
