package com.example.Used_Inst_market.controller.category;

import com.example.Used_Inst_market.model.dto.category.brand.BrandInsertDTO;
import com.example.Used_Inst_market.model.dto.category.brand.BrandUpdateDTO;
import com.example.Used_Inst_market.model.dto.category.lower.LowerCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.lower.LowerCategoryUpdateDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryUpdateDTO;
import com.example.Used_Inst_market.model.vo.category.BrandVO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
import com.example.Used_Inst_market.service.category.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
            @ApiParam(name = "상위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        return categoryService.upperCategorySelect(upperCategoryNo);
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
            @ApiParam(name = "상위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        categoryService.upperCategoryDelete(upperCategoryNo);
    }

    @ApiOperation(value = "하위 카테고리 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info")
    public LowerCategoryVO lowerCategorySelect(
            @ApiParam(name = "하위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(name = "no") @Valid Long lowerCategoryNo) {
        return categoryService.lowerCategorySelect(lowerCategoryNo);
    }

    @ApiOperation(value = "상위 카테고리로 하위 카테고리 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info/upper")
    public List<LowerCategoryVO> lowerCategorySelectFromUpperCategory(
            @ApiParam(name = "상위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(name = "no") @Valid Long upperCategoryNo) {
        return categoryService.lowerCategorySelectFromUpperCategory(upperCategoryNo);
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
            @ApiParam(name = "하위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(name = "no") @Valid Long lowerCategoryNo) {
        categoryService.lowerCategoryDelete(lowerCategoryNo);
    }

    @ApiOperation(value = "브랜드 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info")
    public BrandVO brandSelect(
            @ApiParam(name = "브랜드 번호", required = true, value = "brandNo", example = "1")
            @RequestParam(name = "no") @Valid Long brandNo) {
        return categoryService.brandSelect(brandNo);
    }

    @ApiOperation(value = "하위 카테고리로 브랜드 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/brand/info/lower")
    public List<BrandVO> brandSelectFromLowerCategory(
            @ApiParam(name = "하위 카테고리 번호", required = true, value = "upperCategoryNo", example = "1")
            @RequestParam(value = "no") @Valid Long lowerCategoryNo) {
        return categoryService.brandSelectFromLowerCategory(lowerCategoryNo);
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
    public void delete(
            @ApiParam(name = "브랜드 번호", required = true, value = "brandNo", example = "1")
            @RequestParam(name = "no") @Valid Long brandNo) {
        categoryService.brandDelete(brandNo);
    }
}
