package com.example.Used_Inst_market.controller.category;

import com.example.Used_Inst_market.model.dto.category.brand.*;
import com.example.Used_Inst_market.model.dto.category.lower.*;
import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryDeleteDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategorySelectDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryUpdateDTO;
import com.example.Used_Inst_market.model.vo.category.BrandVO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        UpperCategorySelectDTO upperCategorySelectDTO = UpperCategorySelectDTO.builder()
                .upperCategoryNo(upperCategoryNo)
                .build();

        return categoryService.upperCategorySelect(upperCategorySelectDTO);
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
            @RequestBody @Valid UpperCategoryInsertDTO upperCategoryInsertDTO) {
        return categoryService.upperCategoryInsert(upperCategoryInsertDTO);
    }

    @ApiOperation(value = "상위 카테고리 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/upper/info")
    public Long upperCategoryUpdate(
            @RequestBody @Valid UpperCategoryUpdateDTO upperCategoryUpdateDTO) {
        return categoryService.upperCategoryUpdate(upperCategoryUpdateDTO);
    }

    @ApiOperation(value = "상위 카테고리 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/upper/info")
    public void upperCategoryDelete(
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        UpperCategoryDeleteDTO upperCategoryDeleteDTO = UpperCategoryDeleteDTO.builder()
                .upperCategoryNo(upperCategoryNo)
                .build();

        categoryService.upperCategoryDelete(upperCategoryDeleteDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info")
    public LowerCategoryVO lowerCategorySelect(
            @RequestParam(name = "no") @Valid Long lowerCategoryNo) {
        LowerCategorySelectDTO lowerCategorySelectDTO = LowerCategorySelectDTO.builder()
                .lowerCategoryNo(lowerCategoryNo)
                .build();

        return categoryService.lowerCategorySelect(lowerCategorySelectDTO);
    }

    @ApiOperation(value = "상위 카테고리로 하위 카테고리 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info/upper")
    public List<LowerCategoryVO> lowerCategorySelectFromUpperCategory(
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        LowerCategorySelectFromUpperDTO lowerCategorySelectFromUpperDTO =
                LowerCategorySelectFromUpperDTO.builder()
                        .upperCategoryNo(upperCategoryNo)
                        .build();

        return categoryService
                .lowerCategorySelectFromUpperCategory(lowerCategorySelectFromUpperDTO);
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
            @RequestBody @Valid LowerCategoryInsertDTO lowerCategoryInsertDTO) {
        return categoryService.lowerCategoryInsert(lowerCategoryInsertDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/lower/info")
    public Long lowerCategoryUpdate(
            @RequestBody @Valid LowerCategoryUpdateDTO lowerCategoryUpdateDTO) {
        return categoryService.lowerCategoryUpdate(lowerCategoryUpdateDTO);
    }

    @ApiOperation(value = "하위 카테고리 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/lower/info")
    public void lowerCategoryDelete(
            @RequestParam(name = "no") @Valid Long lowerCategoryNo) {
        LowerCategoryDeleteDTO lowerCategoryDeleteDTO = LowerCategoryDeleteDTO.builder()
                .lowerCategoryNo(lowerCategoryNo)
                .build();

        categoryService.lowerCategoryDelete(lowerCategoryDeleteDTO);
    }

    @ApiOperation(value = "브랜드 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info")
    public BrandVO brandSelect(@RequestParam(name = "no") @Valid Long brandNo) {
        BrandSelectDTO brandSelectDTO = BrandSelectDTO.builder()
                .brandNo(brandNo)
                .build();

        return categoryService.brandSelect(brandSelectDTO);
    }

    @ApiOperation(value = "하위 카테고리로 브랜드 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info/lower")
    public List<BrandVO> brandSelectFromLowerCategory(
            @RequestParam(value = "no") @Valid Long lowerCategoryNo) {
        BrandSelectFromLowerDTO brandSelectFromLowerDTO =
                BrandSelectFromLowerDTO.builder()
                        .lowerCategoryNo(lowerCategoryNo)
                        .build();

        return categoryService.brandSelectFromLowerCategory(brandSelectFromLowerDTO);
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
    public Long insert(@RequestBody @Valid BrandInsertDTO brandInsertDTO) {
        return categoryService.brandInsert(brandInsertDTO);
    }

    @ApiOperation(value = "브랜드 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/brand/info")
    public Long brandUpdate(@RequestBody @Valid BrandUpdateDTO brandUpdateDTO) {
        return categoryService.brandUpdate(brandUpdateDTO);
    }

    @ApiOperation(value = "브랜드 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/brand/info")
    public void delete(@RequestParam(name = "no") @Valid Long brandNo) {
        BrandDeleteDTO brandDeleteDTO = BrandDeleteDTO.builder()
                .brandNo(brandNo)
                .build();

        categoryService.brandDelete(brandDeleteDTO);
    }
}
