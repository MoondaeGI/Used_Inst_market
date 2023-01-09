package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.comment.Comment;
import com.example.Used_Inst_market.model.vo.user.UserVO;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class CommentVO {
    @ApiParam(name = "댓글 번호", required = true, value = "commentNo", example = "1")
    private Long commentNo;
    @ApiParam(name = "유저", required = true, value = "user")
    private UserVO user;
    @ApiParam(name = "유저 이름", required = true, value = "userName", example = "example")
    private String userName;
    @ApiParam(name = "게시글", required = true, value = "post")
    private PostVO post;
    @ApiParam(name = "댓글 내용", required = true, value = "content", example = "example")
    private String content;
    @ApiParam(name = "등록 일자", required = true, value = "regDt", example = "2023-01-09 00:00:00")
    private LocalDateTime regDt;

    private CommentVO(Comment comment) {
        this.commentNo = comment.getCommentNo();
        this.user = UserVO.from(comment.getUser());
        this.userName = this.user.getName();
        this.post = PostVO.from(comment.getPost());
        this.content = comment.getContent();
        this.regDt = comment.getRegDt();
    }

    public static CommentVO from(Comment comment) { return new CommentVO(comment); }
}
