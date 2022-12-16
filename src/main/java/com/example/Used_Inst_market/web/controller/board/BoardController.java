package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.web.dto.board.post.SelectFromContentRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.SelectFromTitleOrContentRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.SelectFromTitleRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromLowerCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromUpperCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromLowerLoRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpperLoRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"게시판 API"})
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list")
    public List<PostVO> selectAll() {
        return boardService.selectAll();
    }

    @ApiOperation(value = "제목으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/title")
    public List<PostVO> selectFromTitle(String keyword) {
        SelectFromTitleRequestDTO selectFromTitleRequestDTO =
                SelectFromTitleRequestDTO.builder()
                        .keyword(keyword)
                        .build();

        return boardService.selectFromTitle(selectFromTitleRequestDTO);
    }

    @ApiOperation(value = "내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/content")
    public List<PostVO> selectFromContent(String keyword) {
        SelectFromContentRequestDTO selectFromContentRequestDTO =
                SelectFromContentRequestDTO.builder()
                        .keyword(keyword)
                        .build();

        return boardService.selectFromContent(selectFromContentRequestDTO);
    }

    @ApiOperation(value = "제목과 내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/all")
    public List<PostVO> selectFromTitleOrContent(String keyword) {
        SelectFromTitleOrContentRequestDTO selectFromTitleOrContentRequestDTO =
                SelectFromTitleOrContentRequestDTO.builder()
                        .keyword(keyword)
                        .build();

        return boardService
                .selectFromTitleOrContent(selectFromTitleOrContentRequestDTO);
    }

    @ApiOperation(value = "상위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
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

    @ApiOperation(value = "하위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
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

    @ApiOperation(value = "브랜드로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/brand")
    public List<PostVO> brandSelect(
            @RequestParam("no") Long brandNo) {
        SelectFromBrandRequestDTO selectFromBrandRequestDTO =
                SelectFromBrandRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        return boardService.brandSelect(selectFromBrandRequestDTO);
    }

    @ApiOperation(value = "상위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
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

    @ApiOperation(value = "하위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
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
