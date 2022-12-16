package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.*;
import com.example.Used_Inst_market.model.vo.board.PostVO;
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
            @RequestPart(value = "images", required = false)
            List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") PostUpdateRequestDTO postUpdateRequestDTO)
            throws IOException {
        if(!multipartFiles.isEmpty()) {
            PictureUpdateRequestDTO pictureUpdateRequestDTO =
                    PictureUpdateRequestDTO.builder()
                            .postNO(postUpdateRequestDTO.getPostNo())
                            .multipartFiles(multipartFiles)
                            .build();
            pictureService.update(pictureUpdateRequestDTO);
        }

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

        PictureDeleteRequestDTO pictureDeleteRequestDTO =
                PictureDeleteRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        pictureService.delete(pictureDeleteRequestDTO);
        postService.delete(postDeleteRequestDTO);
    }
}
