package com.example.Used_Inst_market.web.controller.category;

import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.web.vo.category.UpperCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/upper/info")
    public UpperCategoryVO upperCategorySelect(
            @RequestParam(name = "no") Long upperCategoryNo) {
        UpperCategorySelectRequestDTO upperCategorySelectRequestDTO =
                UpperCategorySelectRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return categoryService.upperCategorySelect(upperCategorySelectRequestDTO);
    }

    @GetMapping("/upper/info/list")
    public List<UpperCategoryVO> upperCategorySelectAll() {
        return categoryService.upperCategorySelectAll();
    }

    @PostMapping("/upper/info")
    public Long upperCategoryInsert(
            @RequestBody UpperCategoryInsertRequestDTO upperCategoryInsertRequestDTO) {
        return categoryService.upperCategoryInsert(upperCategoryInsertRequestDTO);
    }

    @PutMapping("/upper/info")
    public Long upperCategoryUpdate(
            @RequestBody UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO) {
        return categoryService.upperCategoryUpdate(upperCategoryUpdateRequestDTO);
    }

    @DeleteMapping("/upper/info")
    public void upperCategoryDelete(
            @RequestParam(name = "no") Long upperCategoryNo) {
        UpperCategoryDeleteRequestDTO upperCategoryDeleteRequestDTO =
                UpperCategoryDeleteRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        categoryService.upperCategoryDelete(upperCategoryDeleteRequestDTO);
    }

    @GetMapping("/lower/info")
    public LowerCategoryVO lowerCategorySelect(
            @RequestParam(name = "no") Long lowerCategoryNo) {
        LowerCategorySelectRequestDTO lowerCategorySelectRequestDTO =
                LowerCategorySelectRequestDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        return categoryService.lowerCategorySelect(lowerCategorySelectRequestDTO);
    }

    @GetMapping("/lower/info/list")
    public List<LowerCategoryVO> lowerCategorySelectAll() {
        return categoryService.lowerCategorySelectAll();
    }

    @PostMapping("/lower/info")
    public Long lowerCategoryInsert(
            @RequestBody LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO) {
        return categoryService.lowerCategoryInsert(lowerCategoryInsertRequestDTO);
    }

    @PutMapping("/lower/info")
    public Long lowerCategoryUpdate(
            @RequestBody LowerCategoryUpdateRequestDTO lowerCategoryUpdateRequestDTO) {
        return categoryService.lowerCategoryUpdate(lowerCategoryUpdateRequestDTO);
    }

    @DeleteMapping("/lower/info")
    public void lowerCategoryDelete(
            @RequestParam(name = "no") Long lowerCategoryNo) {
        LowerCategoryDeleteRequestDTO lowerCategoryDeleteRequestDTO =
                LowerCategoryDeleteRequestDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        categoryService.lowerCategoryDelete(lowerCategoryDeleteRequestDTO);
    }
}
