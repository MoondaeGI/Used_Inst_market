package com.example.Used_Inst_market.model.dto.board.post;

import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateSoldYNDTO {
    @ApiParam(name = "게시글 번호", required = true,
            value = "postNo", example = "1")
    private Long postNo;
    @ApiParam(name = "판매 여부", required = true,
            value = "soldYN", example = "SALE")
    private SoldYN soldYN;

    @Builder
    public PostUpdateSoldYNDTO(Long postNo, SoldYN soldYN) {
        this.postNo = postNo;
        this.soldYN = soldYN;
    }
}
