package com.example.Used_Inst_market.model.dto.board.comment;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentInsertDTO {
    @ApiParam(name = "댓글 내용", required = true, value = "content", example = "example")
    private String content;
    @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
    private Long postNo;
    @ApiParam(name = "유저 번호", required = true, value = "userNo", example = "1")
    private Long userNo;
}
