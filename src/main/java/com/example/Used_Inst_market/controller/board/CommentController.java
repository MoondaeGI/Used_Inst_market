package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.dto.board.comment.CommentInsertDTO;
import com.example.Used_Inst_market.model.dto.board.comment.CommentUpdateDTO;
import com.example.Used_Inst_market.model.vo.board.CommentVO;
import com.example.Used_Inst_market.service.board.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"댓글 API"})
@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    public CommentVO select(@RequestParam("no") Long commentNo) {
        return commentService.select(commentNo);
    }

    @ApiOperation(value = "게시글 내 댓글 전체 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/post")
    public List<CommentVO> selectFromPost(@RequestParam("no") Long postNo) {
        return commentService.selectFromPost(postNo);
    }

    @ApiOperation(value = "댓글 정보 삽입 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save/info")
    public Long insert(CommentInsertDTO commentInsertDTO) {
        return commentService.insert(commentInsertDTO);
    }

    @ApiOperation(value = "댓글 정보 수정 API")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/info")
    public Long update(CommentUpdateDTO commentUpdateDTO) {
        return commentService.update(commentUpdateDTO);
    }

    @ApiOperation(value = "댓글 정보 삭제 API")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/info")
    public void delete(@RequestParam("no") Long commentNo) {
        commentService.delete(commentNo);
    }

}
