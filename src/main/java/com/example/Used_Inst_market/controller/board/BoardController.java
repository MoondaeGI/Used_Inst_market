package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.dto.board.post.PostSelectFromPriceDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.service.board.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<PostVO> selectFromTitle(
            @ApiParam(name = "검색 키워드", required = true, value = "keyword", example = "example")
            @RequestParam @Valid String keyword) {
        return boardService.selectFromTitle(keyword);
    }

    @ApiOperation(value = "내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/content")
    public List<PostVO> selectFromContent(
            @ApiParam(name = "검색 키워드", required = true, value = "keyword", example = "example")
            @RequestParam @Valid String keyword) {
        return boardService.selectFromContent(keyword);
    }

    @ApiOperation(value = "제목과 내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/all")
    public List<PostVO> selectFromTitleOrContent(
            @ApiParam(name = "검색 키워드", required = true, value = "keyword", example = "example")
            @RequestParam @Valid String keyword) {
        return boardService.selectFromTitleOrContent(keyword);
    }

    @ApiOperation(value = "가격으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/info/list/price")
    public List<PostVO> selectFromPrice(
            @RequestBody @Valid PostSelectFromPriceDTO postSelectFromPriceDTO) {
        return boardService.selectFromPrice(postSelectFromPriceDTO);
    }

    @ApiOperation(value = "상위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/upper")
    public List<PostVO> upperCategorySelect(
            @ApiParam(name = "상위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam("no") Long upperCategoryNo) {
        return boardService.upperCategorySelect(upperCategoryNo);
    }

    @ApiOperation(value = "하위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/lower")
    public List<PostVO> lowerCategorySelect(
            @ApiParam(name = "하위 카테고리 번호", required = true, value = "lowerCategoryNo", example = "1")
            @RequestParam("no") Long localCategoryNo) {
        return boardService.lowerCategorySelect(localCategoryNo);
    }

    @ApiOperation(value = "브랜드로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/brand")
    public List<PostVO> brandSelect(
            @ApiParam(name = "브랜드 번호", required = true, value = "brandNo", example = "1")
            @RequestParam("no") Long brandNo) {
        return boardService.brandSelect(brandNo);
    }

    @ApiOperation(value = "상위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/local/upper")
    public List<PostVO> upperLocalSelect(
            @ApiParam(name = "상위 지역 번호", required = true, value = "upperLocalNo", example = "1")
            @RequestParam("no") Long upperLocalNo) {
        return boardService.upperLocalSelect(upperLocalNo);
    }

    @ApiOperation(value = "하위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/local/lower")
    public List<PostVO> lowerLocalSelect(
            @ApiParam(name = "하위 지역 번호", required = true, value = "lowerLocalNo", example = "1")
            @RequestParam("no") Long lowerLocalNo) {
        return boardService.lowerLocalSelect(lowerLocalNo);
    }
}
