package com.example.Used_Inst_market.web.controller.board;

import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromCityRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromLocalRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("post")
@RestController
public class BoardController {
    private final PostService postService;
    private final PictureService pictureService;

    @GetMapping("/info")
    public PostVO select(@RequestParam(name = "no") Long postNo) {
        PictureSelectRequestDTO pictureSelectRequestDTO =
                PictureSelectRequestDTO.builder()
                        .postNo(postNo)
                        .build();
        List<PictureVO> pictures = pictureService
                .selectAllByPost(pictureSelectRequestDTO);

        PostSelectRequestDTO postSelectRequestDTO =
                PostSelectRequestDTO.builder()
                        .postNo(postNo)
                        .pictures(pictures)
                        .build();

        return postService.select(postSelectRequestDTO);
    }

    @GetMapping("/info/list")
    public List<PostVO> selectAll() {
        return postService.selectAll();
    }

    @PostMapping("/info")
    public Long insert(
            @RequestPart(value = "images") List<MultipartFile> multipartFiles,
            @RequestPart(value = "insertDTO")
            PostInsertRequestDTO postInsertRequestDTO)
            throws IOException {
        Long postNo = postService.insert(postInsertRequestDTO);

        PictureInsertRequestDTO pictureInsertRequestDTO =
                PictureInsertRequestDTO.builder()
                        .postNo(postNo)
                        .multipartFiles(multipartFiles)
                        .build();

        pictureService.insert(pictureInsertRequestDTO);

        return postNo;
    }

    @PutMapping("/info")
    public Long update(
            @RequestPart(value = "pictureUpdateRequestDTO", required = false)
            List<MultipartFile> multipartFiles,
            @RequestPart(value = "postUpdateRequestDTO")
            PostUpdateRequestDTO postUpdateRequestDTO)
            throws IOException {
        Long postNo = postService.update(postUpdateRequestDTO);

        PictureUpdateRequestDTO pictureUpdateRequestDTO =
                PictureUpdateRequestDTO.builder()
                        .postNo(postNo)
                        .multipartFiles(multipartFiles)
                        .build();
        pictureService.update(pictureUpdateRequestDTO);

        return postNo;
    }

    @DeleteMapping("/info")
    public void delete(@RequestParam(name = "no") Long postNo) {
        PostDeleteRequestDTO postDeleteRequestDTO =
                PostDeleteRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        postService.delete(postDeleteRequestDTO);
    }

    @GetMapping("/info/category/upper")
    public List<PostVO> selectFromUpperCategory(
            @RequestParam(name = "no") Long upperCategoryNo) {
        SelectFromUpperRequestDTO selectFromUpperRequestDTO =
                SelectFromUpperRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return postService.selectFromUpperCategory(selectFromUpperRequestDTO);
    }

    @GetMapping("/info/category/lower")
    public List<PostVO> selectFromLowerCategory(
            @RequestParam(name = "no") Long lowerCategoryNo) {
        SelectFromLowerRequestDTO selectFromLowerRequestDTO =
                SelectFromLowerRequestDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        return postService.selectFromLowerCategory(selectFromLowerRequestDTO);
    }

    @GetMapping("/info/category/brand")
    public List<PostVO> selectFromBrand(
            @RequestParam(name = "no") Long brandNo) {
        SelectFromBrandRequestDTO selectFromBrandRequestDTO =
                SelectFromBrandRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        return postService.selectFromBrand(selectFromBrandRequestDTO);
    }

    @GetMapping("/info/local")
    public List<PostVO> selectFromLocal(
            @RequestParam(name = "no") Long localNo) {
        SelectFromLocalRequestDTO selectFromLocalRequestDTO =
                SelectFromLocalRequestDTO.builder()
                        .localNo(localNo)
                        .build();

        return postService.selectFromLocal(selectFromLocalRequestDTO);
    }

    @GetMapping("/info/local/city")
    public List<PostVO> selectFromCity(
            @RequestParam(name = "no") Long cityNo) {
        SelectFromCityRequestDTO selectFromCityRequestDTO =
                SelectFromCityRequestDTO.builder()
                        .cityNo(cityNo)
                        .build();

        return postService.selectFromCity(selectFromCityRequestDTO);
    }
}
