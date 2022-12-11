package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    private final PostService postService;
    private final BoardService boardService;

    @PostMapping("/info")
    public Long insert(
            @RequestPart(value = "images") List<MultipartFile> multipartFiles,
            @RequestPart(value = "insertDTO") PostInsertRequestDTO postInsertRequestDTO)
            throws IOException {
        return postService.insert(postInsertRequestDTO, multipartFiles);
    }

    @DeleteMapping("/info")
    public void delete(@RequestParam(name = "no") Long postNo) {
        PostDeleteRequestDTO postDeleteRequestDTO =
                PostDeleteRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        postService.delete(postDeleteRequestDTO);
    }

    @GetMapping("/info/list")
    public List<PostVO> selectAll() {
        return boardService.selectAll();
    }
}
