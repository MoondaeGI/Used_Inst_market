package com.example.Used_Inst_market.web.controller.post;

import com.example.Used_Inst_market.service.post.PostService;
import com.example.Used_Inst_market.web.dto.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromCityRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromLocalRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("post")
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/info")
    public PostVO select(@RequestParam(name = "no") Long postNo) {
        PostSelectRequestDTO postSelectRequestDTO =
                PostSelectRequestDTO.builder()
                        .postNo(postNo)
                        .build();

        return postService.select(postSelectRequestDTO);
    }

    @GetMapping("/info/list")
    public List<PostVO> selectAll() {
        return postService.selectAll();
    }

    @PostMapping("/info")
    public Long insert(
            @RequestBody PostInsertRequestDTO postInsertRequestDTO) {
        return postService.insert(postInsertRequestDTO);
    }

    @PutMapping("/info")
    public Long update(
            @RequestBody PostUpdateRequestDTO postUpdateRequestDTO) {
        return postService.update(postUpdateRequestDTO);
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
