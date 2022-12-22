package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromContentDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromPriceDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromTitleOrContentDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromTitleDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromBrandDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromLoCategoryDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromUpCategoryDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.PostSelectFromLoLocalDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpLocalDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        PostSelectFromTitleDTO postSelectFromTitleDTO = PostSelectFromTitleDTO.builder()
                .keyword(keyword)
                .build();

        return boardService.selectFromTitle(postSelectFromTitleDTO);
    }

    @ApiOperation(value = "내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/content")
    public List<PostVO> selectFromContent(String keyword) {
        PostSelectFromContentDTO postSelectFromContentDTO =
                PostSelectFromContentDTO.builder()
                        .keyword(keyword)
                        .build();

        return boardService.selectFromContent(postSelectFromContentDTO);
    }

    @ApiOperation(value = "제목과 내용으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list/all")
    public List<PostVO> selectFromTitleOrContent(String keyword) {
        PostSelectFromTitleOrContentDTO postSelectFromTitleOrContentDTO =
                PostSelectFromTitleOrContentDTO.builder()
                        .keyword(keyword)
                        .build();

        return boardService
                .selectFromTitleOrContent(postSelectFromTitleOrContentDTO);
    }

    @ApiOperation(value = "가격으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/info/list/price")
    public List<PostVO> selectFromPrice(
            PostSelectFromPriceDTO postSelectFromPriceDTO) {
        return boardService.selectFromPrice(postSelectFromPriceDTO);
    }

    @ApiOperation(value = "상위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/upper")
    public List<PostVO> upperCategorySelect(
            @RequestParam("no") Long upperCategoryNo) {
        PostSelectFromUpCategoryDTO postSelectFromUpCategoryDTO =
                PostSelectFromUpCategoryDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return boardService
                .upperCategorySelect(postSelectFromUpCategoryDTO);
    }

    @ApiOperation(value = "하위 카테고리로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/lower")
    public List<PostVO> lowerCategorySelect(
            @RequestParam("no") Long localCategoryNo) {
        PostSelectFromLoCategoryDTO selectFromLowerCtRequestDTO =
                PostSelectFromLoCategoryDTO.builder()
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
        PostSelectFromBrandDTO postSelectFromBrandDTO = PostSelectFromBrandDTO.builder()
                .brandNo(brandNo)
                .build();

        return boardService.brandSelect(postSelectFromBrandDTO);
    }

    @ApiOperation(value = "상위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/local/upper")
    public List<PostVO> upperLocalSelect(
            @RequestParam("no") Long upperLocalNo) {
        SelectFromUpLocalDTO selectFromUpLocalDTO = SelectFromUpLocalDTO.builder()
                .upperLocalNo(upperLocalNo)
                .build();

        return boardService.upperLocalSelect(selectFromUpLocalDTO);
    }

    @ApiOperation(value = "하위 지역으로 게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/local/lower")
    public List<PostVO> lowerLocalSelect(
            @RequestParam("no") Long lowerLocalNo) {
        PostSelectFromLoLocalDTO postSelectFromLoLocalDTO = PostSelectFromLoLocalDTO.builder()
                .lowerLocalNo(lowerLocalNo)
                .build();

        return boardService.lowerLocalSelect(postSelectFromLoLocalDTO);
    }
}
