package com.example.Used_Inst_market.web.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSelectRequestDTO {
    private Long productNo;

    @Builder
    public ProductSelectRequestDTO(Long productNo) {
        this.productNo = productNo;
    }
}
