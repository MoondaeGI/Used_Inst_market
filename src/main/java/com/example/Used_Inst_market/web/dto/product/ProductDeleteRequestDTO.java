package com.example.Used_Inst_market.web.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDeleteRequestDTO {
    private Long productNo;

    public ProductDeleteRequestDTO(Long productNo) {
        this.productNo = productNo;
    }
}
