package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.dto.category.brand.*;
import com.example.Used_Inst_market.model.dto.category.lower.*;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryDeleteDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.model.dto.category.upper.UpperCategorySelectDTO;
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
    public UpperCategoryVO upperCategorySelect(final UpperCategorySelectDTO upperCategorySelectDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategorySelectDTO.getUpperCategoryNo())
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
    public Long upperCategoryInsert(final UpperCategoryInsertDTO upperCategoryInsertDTO) {
        return upperCategoryRepository
                .save(upperCategoryInsertDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long upperCategoryUpdate(final UpperCategoryUpdateDTO upperCategoryUpdateDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateDTO.getName());

        return upperCategoryUpdateDTO.getUpperCategoryNo();
    }

    @Transactional
    public void upperCategoryDelete(final UpperCategoryDeleteDTO upperCategoryDeleteDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryDeleteDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategoryRepository.delete(upperCategory);
    }

    @Transactional(readOnly = true)
    public LowerCategoryVO lowerCategorySelect(final LowerCategorySelectDTO lowerCategorySelectDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategorySelectDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return LowerCategoryVO.from(lowerCategory);
    }

    @Transactional(readOnly = true)
    public List<LowerCategoryVO> lowerCategorySelectFromUpperCategory(
            final LowerCategorySelectFromUpperDTO lowerCategorySelectFromUpperDTO) {
        final UpperCategory upperCategory = upperCategoryRepository
                .findById(lowerCategorySelectFromUpperDTO.getUpperCategoryNo())
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
    public Long lowerCategoryInsert(final LowerCategoryInsertDTO lowerCategoryInsertDTO) {
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
    public Long lowerCategoryUpdate(final LowerCategoryUpdateDTO lowerCategoryUpdateDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(lowerCategoryUpdateDTO.getUpperCategory(),
                lowerCategoryUpdateDTO.getName());

        return lowerCategoryUpdateDTO.getLowerCategoryNo();
    }

    @Transactional
    public void lowerCategoryDelete(final LowerCategoryDeleteDTO lowerCategoryDeleteDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryDeleteDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }

    @Transactional(readOnly = true)
    public BrandVO brandSelect(final BrandSelectDTO brandSelectDTO) {
        final Brand brand = brandRepository.findById(brandSelectDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return BrandVO.from(brand);
    }

    @Transactional(readOnly = true)
    public List<BrandVO> brandSelectFromLowerCategory(final BrandSelectFromLowerDTO brandSelectFromLowerDTO) {
        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(brandSelectFromLowerDTO.getLowerCategoryNo())
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
    public Long brandInsert(final BrandInsertDTO brandInsertDTO) {
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
    public Long brandUpdate(final BrandUpdateDTO brandUpdateDTO) {
        final Brand brand = brandRepository.findById(brandUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        brand.update(brandUpdateDTO.getLowerCategory(), brandUpdateDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void brandDelete(final BrandDeleteDTO brandDeleteDTO) {
        final Brand brand = brandRepository.findById(brandDeleteDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
