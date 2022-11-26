package com.example.Used_Inst_market.web.dto.product;

import com.example.Used_Inst_market.domain.brand.Brand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDTO {
    private Long productNo;
    private Brand brand;
    private String name;
    private Integer price;

    @Builder
    public ProductUpdateRequestDTO(Long productNo, Brand brand, String name, Integer price) {
        this.productNo = productNo;
        this.brand = brand;
        this.name = name;
        this.price = price;
    }
}
