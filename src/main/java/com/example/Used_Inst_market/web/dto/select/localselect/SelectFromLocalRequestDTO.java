package com.example.Used_Inst_market.web.dto.select.localselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromLocalRequestDTO {
    Long localNo;

    @Builder
    public SelectFromLocalRequestDTO(Long localNo) {
        this.localNo = localNo;
    }
}
