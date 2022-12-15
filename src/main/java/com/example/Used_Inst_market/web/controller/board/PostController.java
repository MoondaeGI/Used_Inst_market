package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromLowerCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromUpperCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromLowerLoRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpperLoRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = {"게시판 API"})
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final PostService postService;
    private final PictureService pictureService;
    private final BoardService boardService;

    @ApiOperation(value = "게시글 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    public PostVO select(
            @RequestParam("no") Long postNo) throws IOException {
        PostSelectRequestDTO postSelectRequestDTO =
                PostSelectRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        return postService.select(postSelectRequestDTO);
    }

    @ApiOperation(value = "게시글 정보 삽입 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/info")
    public Long insert(
            @RequestPart(value = "images") List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") PostInsertRequestDTO postInsertRequestDTO)
            throws IOException {
        Long postNo = postService.insert(postInsertRequestDTO);

        PictureInsertRequestDTO pictureInsertRequestDTO =
                PictureInsertRequestDTO.builder()
                        .postNo(postNo)
                        .multipartFiles(multipartFiles)
                        .build();

        return pictureService.insert(pictureInsertRequestDTO);
    }

    @ApiOperation(value = "게시글 정보 수정 API")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/info")
    public Long update(
            @RequestPart(value = "images") List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") PostUpdateRequestDTO postUpdateRequestDTO)
            throws IOException {
        PictureUpdateRequestDTO pictureUpdateRequestDTO =
                PictureUpdateRequestDTO.builder()
                        .postNO(postUpdateRequestDTO.getPostNo())
                        .multipartFiles(multipartFiles)
                        .build();
        pictureService.update(pictureUpdateRequestDTO);

        return postService.update(postUpdateRequestDTO);
    }

    @ApiOperation(value = "게시글 정보 삭제 API")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/info")
    public void delete(@RequestParam(name = "no") Long postNo)
            throws IOException {
        PostDeleteRequestDTO postDeleteRequestDTO =
                PostDeleteRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        postService.delete(postDeleteRequestDTO);
    }

    @ApiOperation(value = "게시글 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info/list")
    public List<PostVO> selectAll() {
        return boardService.selectAll();
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
