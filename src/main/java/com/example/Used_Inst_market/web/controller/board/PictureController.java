package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectByPostRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/picture")
@RestController
public class PictureController {
    private final PictureService pictureService;

    @CrossOrigin
    @GetMapping("/info")
    public PictureVO select(
            @RequestParam("no") Long pictureNo) throws IOException {
        PictureSelectRequestDTO pictureSelectRequestDTO =
                PictureSelectRequestDTO.builder()
                        .pictureNo(pictureNo)
                        .build();

        return pictureService.select(pictureSelectRequestDTO);
    }

    @CrossOrigin
    @GetMapping("info/post")
    public List<PictureVO> selectByPost(
            @RequestParam("no") Long postNo) throws IOException {
        PictureSelectByPostRequestDTO pictureSelectByPostRequestDTO =
                PictureSelectByPostRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        return pictureService.selectByPost(pictureSelectByPostRequestDTO);
    }
}
