package com.example.Used_Inst_market.web.dto.board.post;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromPriceRequestDTO {
    @ApiParam(name = "최소 가격", readOnly = true,
            value = "lessPrice", example = "1")
    private Integer lessPrice;
    @ApiParam(name = "최대 가격", readOnly = true,
            value = "greaterPrice", example = "1")
    private Integer greaterPrice;

    @Builder
    public SelectFromPriceRequestDTO(
            Integer lessPrice, Integer greaterPrice) {
        this.lessPrice = lessPrice;
        this.greaterPrice = greaterPrice;
    }
}
