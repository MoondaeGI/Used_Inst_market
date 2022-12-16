package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectByPostDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = {"이미지 API"})
@RequiredArgsConstructor
@RequestMapping("/picture")
@RestController
public class PictureController {
    private final PictureService pictureService;

    @ApiOperation(value = "이미지 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @CrossOrigin
    @GetMapping("/info")
    public PictureVO select(
            @RequestParam("no") Long pictureNo) throws IOException {
        PictureSelectDTO pictureSelectDTO =
                PictureSelectDTO.builder()
                        .pictureNo(pictureNo)
                        .build();

        return pictureService.select(pictureSelectDTO);
    }

    @ApiOperation(value = "게시글의 모든 이미지 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @CrossOrigin
    @GetMapping("info/post")
    public List<PictureVO> selectByPost(
            @RequestParam("no") Long postNo) throws IOException {
        PictureSelectByPostDTO pictureSelectByPostDTO =
                PictureSelectByPostDTO.builder()
                        .postNo(postNo)
                        .build();

        return pictureService
                .selectByPost(pictureSelectByPostDTO);
    }
}
