package com.example.Used_Inst_market.web.dto.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteBrandRequestDTO {
    private Long brandNo;

    @Builder
    public DeleteBrandRequestDTO(Long brandNo) {
        this.brandNo = brandNo;
    }
}
