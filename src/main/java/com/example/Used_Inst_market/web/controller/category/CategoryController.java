package com.example.Used_Inst_market.web.controller.category;

import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.web.dto.category.brand.BrandDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.category.BrandVO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"카테고리 API"})
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation(value = "상위 카테고리 게시글 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/upper/info")
    public UpperCategoryVO upperCategorySelect(
            @RequestParam(name = "no") Long upperCategoryNo) {
        UpperCategorySelectRequestDTO upperCategorySelectRequestDTO =
                UpperCategorySelectRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return categoryService.upperCategorySelect(upperCategorySelectRequestDTO);
    }

    @ApiOperation(value = "상위 카테고리 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/upper/info/list")
    public List<UpperCategoryVO> upperCategorySelectAll() {
        return categoryService.upperCategorySelectAll();
    }

    @ApiOperation(value = "상위 카테고리 정보 삽입 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upper/info")
    public Long upperCategoryInsert(
            @RequestBody UpperCategoryInsertRequestDTO upperCategoryInsertRequestDTO) {
        return categoryService.upperCategoryInsert(upperCategoryInsertRequestDTO);
    }

    @ApiOperation(value = "상위 카테고리 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/upper/info")
    public Long upperCategoryUpdate(
            @RequestBody UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO) {
        return categoryService.upperCategoryUpdate(upperCategoryUpdateRequestDTO);
    }

    @ApiOperation(value = "상위 카테고리 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/upper/info")
    public void upperCategoryDelete(
            @RequestParam(name = "no") Long upperCategoryNo) {
        UpperCategoryDeleteRequestDTO upperCategoryDeleteRequestDTO =
                UpperCategoryDeleteRequestDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        categoryService.upperCategoryDelete(upperCategoryDeleteRequestDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info")
    public LowerCategoryVO lowerCategorySelect(
            @RequestParam(name = "no") Long lowerCategoryNo) {
        LowerCategorySelectRequestDTO lowerCategorySelectRequestDTO =
                LowerCategorySelectRequestDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        return categoryService.lowerCategorySelect(lowerCategorySelectRequestDTO);
    }

    @ApiOperation(value = "하위 카테고리 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info/list")
    public List<LowerCategoryVO> lowerCategorySelectAll() {
        return categoryService.lowerCategorySelectAll();
    }

    @ApiOperation(value = "하위 카테고리 정보 삽입 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lower/info")
    public Long lowerCategoryInsert(
            @RequestBody LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO) {
        return categoryService.lowerCategoryInsert(lowerCategoryInsertRequestDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/lower/info")
    public Long lowerCategoryUpdate(
            @RequestBody LowerCategoryUpdateRequestDTO lowerCategoryUpdateRequestDTO) {
        return categoryService.lowerCategoryUpdate(lowerCategoryUpdateRequestDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/lower/info")
    public void lowerCategoryDelete(
            @RequestParam(name = "no") Long lowerCategoryNo) {
        LowerCategoryDeleteRequestDTO lowerCategoryDeleteRequestDTO =
                LowerCategoryDeleteRequestDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        categoryService.lowerCategoryDelete(lowerCategoryDeleteRequestDTO);
    }

    @ApiOperation(value = "브랜드 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info")
    public BrandVO brandSelect(@RequestParam(name = "no") Long brandNo) {
        BrandSelectRequestDTO brandSelectRequestDTO =
                BrandSelectRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        return categoryService.brandSelect(brandSelectRequestDTO);
    }

    @ApiOperation(value = "브랜드 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info/list")
    public List<BrandVO> brandSelectAll() {
        return categoryService.brandSelectAll();
    }

    @ApiOperation(value = "브랜드 정보 삽입 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/brand/info")
    public Long insert(@RequestBody BrandInsertRequestDTO brandInsertRequestDTO) {
        return categoryService.brandInsert(brandInsertRequestDTO);
    }

    @ApiOperation(value = "브랜드 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/brand/info")
    public Long brandUpdate(@RequestBody BrandUpdateRequestDTO brandUpdateRequestDTO) {
        return categoryService.brandUpdate(brandUpdateRequestDTO);
    }

    @ApiOperation(value = "브랜드 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/brand/info")
    public void delete(@RequestParam(name = "no") Long brandNo) {
        BrandDeleteRequestDTO brandDeleteRequestDTO =
                BrandDeleteRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        categoryService.brandDelete(brandDeleteRequestDTO);
    }
}
