package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public UpperCategoryVO upperCategorySelect(
            UpperCategorySelectRequestDTO upperCategorySelectRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategorySelectRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return UpperCategoryVO.from(upperCategory);
    }

    @Transactional(readOnly = true)
    public List<UpperCategoryVO> upperCategorySelectAll() {
        return upperCategoryRepository.findAll().stream()
                .map(UpperCategoryVO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long upperCategoryInsert(
            UpperCategoryInsertRequestDTO upperCategoryInsertRequestDTO) {
        return upperCategoryRepository
                .save(upperCategoryInsertRequestDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long upperCategoryUpdate(
            UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateRequestDTO.getName());

        return upperCategoryUpdateRequestDTO.getUpperCategoryNo();
    }

    @Transactional
    public void upperCategoryDelete(
            UpperCategoryDeleteRequestDTO upperCategoryDeleteRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryDeleteRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException());

        upperCategoryRepository.delete(upperCategory);
    }

    @Transactional(readOnly = true)
    public LowerCategoryVO lowerCategorySelect(
            LowerCategorySelectRequestDTO lowerCategorySelectRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategorySelectRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return LowerCategoryVO.from(lowerCategory);
    }

    @Transactional(readOnly = true)
    public List<LowerCategoryVO> lowerCategorySelectAll() {
        return lowerCategoryRepository.findAll().stream()
                .map(LowerCategoryVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long lowerCategoryInsert(
            LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO) {
        return lowerCategoryRepository
                .save(lowerCategoryInsertRequestDTO.toEntity())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long lowerCategoryUpdate(
            LowerCategoryUpdateRequestDTO lowerCategoryUpdateRequestDTO)
            throws IllegalArgumentException {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryUpdateRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(lowerCategoryUpdateRequestDTO.getUpperCategory(),
                lowerCategoryUpdateRequestDTO.getName());

        return lowerCategoryUpdateRequestDTO.getLowerCategoryNo();
    }

    @Transactional
    public void lowerCategoryDelete(
            LowerCategoryDeleteRequestDTO lowerCategoryDeleteRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryDeleteRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }

    @Transactional(readOnly = true)
    public BrandVO brandSelect(BrandSelectRequestDTO brandSelectRequestDTO) {
        Brand brand = brandRepository.findById(brandSelectRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return BrandVO.from(brand);
    }

    @Transactional(readOnly = true)
    public List<BrandVO> brandSelectAll() {
        return brandRepository.findAll().stream()
                .map(BrandVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long brandInsert(BrandInsertRequestDTO brandInsertRequestDTO) {
        return brandRepository.save(brandInsertRequestDTO.toEntity()).getBrandNo();
    }

    @Transactional
    public Long brandUpdate(BrandUpdateRequestDTO brandUpdateRequestDTO) {
        Brand brand = brandRepository.findById(brandUpdateRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        brand.update(brandUpdateRequestDTO.getLowerCategory(), brandUpdateRequestDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void brandDelete(BrandDeleteRequestDTO brandDeleteRequestDTO) {
        Brand brand = brandRepository.findById(brandDeleteRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
