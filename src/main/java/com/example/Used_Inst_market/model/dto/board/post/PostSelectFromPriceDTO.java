package com.example.Used_Inst_market.model.dto.board.post;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostSelectFromPriceDTO {
    @ApiParam(name = "최소 가격", readOnly = true, value = "lessPrice", example = "1")
    private Integer lessPrice;
    @ApiParam(name = "최대 가격", readOnly = true, value = "greaterPrice", example = "1")
    private Integer greaterPrice;
}
