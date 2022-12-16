package com.example.Used_Inst_market.web.dto.board.post;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromContentRequestDTO {
    @ApiParam(name = "검색어", readOnly = true,
            value = "keyword", example = "example")
    private String keyword;

    @Builder
    public SelectFromContentRequestDTO(String keyword) {
        this.keyword = keyword;
    }
}
