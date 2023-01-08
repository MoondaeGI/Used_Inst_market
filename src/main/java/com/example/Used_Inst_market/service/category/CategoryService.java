package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.dto.category.brand.BrandInsertDTO;
import com.example.Used_Inst_market.model.dto.category.brand.BrandUpdateDTO;
import com.example.Used_Inst_market.model.dto.category.lower.LowerCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.lower.LowerCategoryUpdateDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryUpdateDTO;
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
    public UpperCategoryVO upperCategorySelect(Long upperCategoryNo) {
        final UpperCategory upperCategory = upperCategoryRepository.findById(upperCategoryNo)
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
    public Long upperCategoryInsert(UpperCategoryInsertDTO upperCategoryInsertDTO) {
        return upperCategoryRepository
                .save(upperCategoryInsertDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long upperCategoryUpdate(UpperCategoryUpdateDTO upperCategoryUpdateDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateDTO.getName());

        return upperCategoryUpdateDTO.getUpperCategoryNo();
    }

    @Transactional
    public void upperCategoryDelete(Long upperCategoryNo) {
        final UpperCategory upperCategory = upperCategoryRepository.findById(upperCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategoryRepository.delete(upperCategory);
    }

    @Transactional(readOnly = true)
    public LowerCategoryVO lowerCategorySelect(Long lowerCategoryNo) {
        final LowerCategory lowerCategory = lowerCategoryRepository.findById(lowerCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return LowerCategoryVO.from(lowerCategory);
    }

    @Transactional(readOnly = true)
    public List<LowerCategoryVO> lowerCategorySelectFromUpperCategory(Long upperCategoryNo) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return lowerCategoryRepository.findByUpperCategory(upperCategory).stream()
                .map(LowerCategoryVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LowerCategoryVO> lowerCategorySelectAll() {
        return lowerCategoryRepository.findAll().stream()
                .map(LowerCategoryVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long lowerCategoryInsert(LowerCategoryInsertDTO lowerCategoryInsertDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(lowerCategoryInsertDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return lowerCategoryRepository.save(
                LowerCategory.builder()
                        .upperCategory(upperCategory)
                        .name(lowerCategoryInsertDTO.getName())
                        .build())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long lowerCategoryUpdate(LowerCategoryUpdateDTO lowerCategoryUpdateDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final UpperCategory upperCategory = upperCategoryRepository
                .findById(lowerCategoryUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(upperCategory, lowerCategoryUpdateDTO.getName());
        return lowerCategoryUpdateDTO.getLowerCategoryNo();
    }

    @Transactional
    public void lowerCategoryDelete(Long lowerCategoryNo) {
        final LowerCategory lowerCategory = lowerCategoryRepository.findById(lowerCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }

    @Transactional(readOnly = true)
    public BrandVO brandSelect(Long brandNo) {
        final Brand brand = brandRepository.findById(brandNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return BrandVO.from(brand);
    }

    @Transactional(readOnly = true)
    public List<BrandVO> brandSelectFromLowerCategory(Long lowerCategoryNo) {
        final LowerCategory lowerCategory = lowerCategoryRepository.findById(lowerCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return brandRepository.findByLowerCategory(lowerCategory).stream()
                .map(BrandVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BrandVO> brandSelectAll() {
        return brandRepository.findAll().stream()
                .map(BrandVO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long brandInsert(BrandInsertDTO brandInsertDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(brandInsertDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return brandRepository.save(
                Brand.builder()
                        .lowerCategory(lowerCategory)
                        .name(brandInsertDTO.getName())
                        .build())
                .getBrandNo();
    }

    @Transactional
    public Long brandUpdate(BrandUpdateDTO brandUpdateDTO) {
        final Brand brand = brandRepository.findById(brandUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        final LowerCategory lowerCategory = lowerCategoryRepository.findById(brandUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        brand.update(lowerCategory, brandUpdateDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void brandDelete(Long brandNo) {
        final Brand brand = brandRepository.findById(brandNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
