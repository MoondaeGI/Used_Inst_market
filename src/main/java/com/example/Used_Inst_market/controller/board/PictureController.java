package com.example.Used_Inst_market.controller.board;

import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.service.board.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = {"이미지 API"})
@RequiredArgsConstructor
@RequestMapping("/picture")
@RestController
public class PictureController {
    private final PictureService pictureService;

    @ApiOperation(value = "이미지 정보 조회 API")
    @CrossOrigin
    @GetMapping("/info")
    public PictureVO select(
            @ApiParam(name = "이미지 번호", required = true, value = "pictureNo", example = "1")
            @RequestParam("no") @Valid Long pictureNo) throws IOException {
        return pictureService.select(pictureNo);
    }

    @ApiOperation(value = "게시글의 모든 이미지 정보 조회 API")
    @CrossOrigin
    @GetMapping("info/post")
    public List<PictureVO> selectByPost(
            @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
            @RequestParam("no") @Valid Long postNo) throws IOException {
        return pictureService.selectByPost(postNo);
    }
}
