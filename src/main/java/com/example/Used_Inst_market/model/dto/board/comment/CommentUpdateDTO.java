package com.example.Used_Inst_market.model.dto.board.comment;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentUpdateDTO {
    @ApiParam(name = "댓글 번호", required = true, value = "commentNo", example = "1")
    private Long commentNo;
    @ApiParam(name = "댓글 내용", required = true, value = "content", example = "example")
    private String content;
}
