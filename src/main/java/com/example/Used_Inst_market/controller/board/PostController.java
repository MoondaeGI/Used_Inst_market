package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.dto.board.post.*;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.model.dto.board.picture.PictureDeleteDTO;
import com.example.Used_Inst_market.model.dto.board.picture.PictureInsertDTO;
import com.example.Used_Inst_market.model.dto.board.picture.PictureSelectByPostDTO;
import com.example.Used_Inst_market.model.dto.board.picture.PictureUpdateDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public PostVO select(@RequestParam("no") Long postNo) throws IOException {
        PostSelectDTO postSelectDTO = PostSelectDTO.builder()
                .postNo(postNo)
                .build();

        return postService.select(postSelectDTO);
    }

    @ApiOperation(value = "게시글 정보 삽입 API")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save/info")
    public Long insert(
            @RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") @Valid PostInsertDTO postInsertDTO)
            throws IOException {
        Long postNo = postService.insert(postInsertDTO);

        if(!multipartFiles.isEmpty()) {
            PictureInsertDTO pictureInsertDTO = PictureInsertDTO.builder()
                    .postNo(postNo)
                    .multipartFiles(multipartFiles)
                    .build();
            pictureService.insert(pictureInsertDTO);
        }

        return postNo;
    }

    @ApiOperation(value = "게시글 정보 수정 API")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/info")
    public Long update(
            @RequestPart(value = "images", required = false)
            List<MultipartFile> multipartFiles,
            @RequestPart(value = "dto") @Valid PostUpdateDTO postUpdateDTO) throws IOException {
        if(!multipartFiles.isEmpty()) {
            PictureUpdateDTO pictureUpdateDTO = PictureUpdateDTO.builder()
                    .postNO(postUpdateDTO.getPostNo())
                    .multipartFiles(multipartFiles)
                    .build();
            pictureService.update(pictureUpdateDTO);
        }

        return postService.update(postUpdateDTO);
    }

    @ApiOperation(value = "게시글 판매 여부 수정 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/update/soldYN/info")
    public Long updateSoldYN(@RequestParam("no") Long postNo) {
        PostUpdateSoldYNDTO postUpdateSoldYNDTO = PostUpdateSoldYNDTO.builder()
                .postNo(postNo)
                .build();

        return postService.updateSoldYN(postUpdateSoldYNDTO);
    }

    @ApiOperation(value = "게시글 정보 삭제 API")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/info")
    public void delete(@RequestParam(name = "no") Long postNo) throws IOException {
        PostDeleteDTO postDeleteDTO = PostDeleteDTO.builder()
                .postNo(postNo)
                .build();

        PictureSelectByPostDTO pictureSelectByPostDTO = PictureSelectByPostDTO.builder()
                .postNo(postNo)
                .build();

        if(!pictureService.selectByPost(pictureSelectByPostDTO).isEmpty()) {
            PictureDeleteDTO pictureDeleteDTO = PictureDeleteDTO.builder()
                    .postNo(postNo)
                    .build();
            pictureService.delete(pictureDeleteDTO);
        }

        postService.delete(postDeleteDTO);
    }
}
