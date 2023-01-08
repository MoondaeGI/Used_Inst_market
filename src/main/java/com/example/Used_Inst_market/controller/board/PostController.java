package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.dto.board.picture.PictureInsertDTO;
import com.example.Used_Inst_market.model.dto.board.picture.PictureUpdateDTO;
import com.example.Used_Inst_market.model.dto.board.post.PostInsertDTO;
import com.example.Used_Inst_market.model.dto.board.post.PostUpdateDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = {"게시글 API"})
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
            @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
            @RequestParam("no") Long postNo) throws IOException {
        return postService.select(postNo);
    }

    @ApiOperation(value = "게시글 판매 여부 수정 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/update/soldYN/info")
    public Long updateSoldYN(
            @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
            @RequestParam("no") Long postNo) {
        return postService.updateSoldYN(postNo);
    }

    @ApiOperation(value = "게시글 정보 삽입 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save/info")
    public Long insert(
            @RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") @Valid PostInsertDTO postInsertDTO)
            throws IOException {
        Long postNo = postService.insert(postInsertDTO);

        PictureInsertDTO pictureInsertDTO = PictureInsertDTO.builder()
                .postNo(postNo)
                .multipartFiles(multipartFiles)
                .build();
        pictureService.insert(pictureInsertDTO);


        return postNo;
    }

    @ApiOperation(value = "게시글 정보 수정 API")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/info")
    public Long update(
            @RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") @Valid PostUpdateDTO postUpdateDTO) throws IOException {
        PictureUpdateDTO pictureUpdateDTO = PictureUpdateDTO.builder()
                .postNo(postUpdateDTO.getPostNo())
                .multipartFiles(multipartFiles)
                .build();
        pictureService.update(pictureUpdateDTO);

        return postService.update(postUpdateDTO);
    }

    @ApiOperation(value = "게시글 정보 삭제 API")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/info")
    public void delete(
            @ApiParam(name = "게시글 번호", readOnly = true, value = "postNo", example = "1")
            @RequestParam(name = "no") Long postNo) throws IOException {
        if(!pictureService.selectByPost(postNo).isEmpty()) {
            pictureService.delete(postNo);
        }
        postService.delete(postNo);
    }
}
