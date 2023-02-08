package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.dto.board.comment.CommentInsertDTO;
import com.example.Used_Inst_market.model.dto.board.comment.CommentUpdateDTO;
import com.example.Used_Inst_market.model.vo.board.CommentVO;
import com.example.Used_Inst_market.service.board.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"댓글 API"})
@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 정보 조회 API")
    @GetMapping("/info")
    public CommentVO select(
            @ApiParam(name = "댓글 번호", required = true, value = "commentNo", example = "1")
            @RequestParam("no") @Valid Long commentNo) {
        return commentService.select(commentNo);
    }

    @ApiOperation(value = "게시글 내 댓글 전체 조회 API")
    @GetMapping("/info/post")
    public List<CommentVO> selectFromPost(
            @ApiParam(name = "게시글 번호", required = true, value = "PostNo", example = "1")
            @RequestParam("no") @Valid Long postNo) {
        return commentService.selectFromPost(postNo);
    }

    @ApiOperation(value = "댓글 정보 삽입 API")
    @PostMapping("/info/save")
    public Long insert(@RequestBody @Valid CommentInsertDTO commentInsertDTO) {
        return commentService.insert(commentInsertDTO);
    }

    @ApiOperation(value = "댓글 정보 수정 API")
    @PutMapping("/info/update")
    public Long update(@RequestBody @Valid CommentUpdateDTO commentUpdateDTO) {
        return commentService.update(commentUpdateDTO);
    }

    @ApiOperation(value = "댓글 정보 삭제 API")
    @DeleteMapping("/info/delete")
    public void delete(
            @ApiParam(name = "댓글 번호", required = true, value = "commentNo", example = "1")
            @RequestParam("no") @Valid Long commentNo) {
        commentService.delete(commentNo);
    }

}
