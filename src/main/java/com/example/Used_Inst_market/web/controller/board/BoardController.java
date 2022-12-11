package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromLowerCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromUpperCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromLowerLoRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpperLoRequestDTO;
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

    @GetMapping("/info")
    public PostVO select(@RequestParam("no") Long postNo) {
        PostSelectRequestDTO postSelectRequestDTO =
                PostSelectRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        return postService.select(postSelectRequestDTO);
    }

    @PostMapping("/info")
    public Long insert(
            @RequestPart(value = "images") List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") PostInsertRequestDTO postInsertRequestDTO)
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

    @GetMapping("/category/upper")
    public List<PostVO> upperCategorySelect(
            @RequestParam("no") Long upperCategoryNo) {
        SelectFromUpperCtRequestDTO selectFromUpperCtRequestDTO =
                SelectFromUpperCtRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return boardService
                .upperCategorySelect(selectFromUpperCtRequestDTO);
    }

    @GetMapping("/category/lower")
    public List<PostVO> lowerCategorySelect(
            @RequestParam("no") Long localCategoryNo) {
        SelectFromLowerCtRequestDTO selectFromLowerCtRequestDTO =
                SelectFromLowerCtRequestDTO.builder()
                        .lowerCategoryNo(localCategoryNo)
                        .build();

        return boardService
                .lowerCategorySelect(selectFromLowerCtRequestDTO);
    }

    @GetMapping("/category/brand")
    public List<PostVO> brandSelect(
            @RequestParam("no") Long brandNo) {
        SelectFromBrandRequestDTO selectFromBrandRequestDTO =
                SelectFromBrandRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        return boardService.brandSelect(selectFromBrandRequestDTO);
    }

    @GetMapping("/local/upper")
    public List<PostVO> upperLocalSelect(
            @RequestParam("no") Long upperLocalNo) {
        SelectFromUpperLoRequestDTO selectFromUpperLoRequestDTO =
                SelectFromUpperLoRequestDTO.builder()
                        .upperLocalNo(upperLocalNo)
                        .build();

        return boardService
                .upperLocalSelect(selectFromUpperLoRequestDTO);
    }

    @GetMapping("/local/lower")
    public List<PostVO> lowerLocalSelect(
            @RequestParam("no") Long lowerLocalNo) {
        SelectFromLowerLoRequestDTO selectFromLowerLoRequestDTO =
                SelectFromLowerLoRequestDTO.builder()
                        .lowerLocalNo(lowerLocalNo)
                        .build();

        return boardService
                .lowerLocalSelect(selectFromLowerLoRequestDTO);
    }
}
