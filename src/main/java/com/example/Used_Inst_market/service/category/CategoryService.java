package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.brand.BrandDeleteDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandInsertDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandSelectDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandUpdateDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryDeleteDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategorySelectDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryUpdateDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryDeleteDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategorySelectDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateDTO;
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
            UpperCategorySelectDTO upperCategorySelectDTO) {
        UpperCategory upperCategory = upperCategoryRepository
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
    public Long upperCategoryInsert(
            UpperCategoryInsertDTO upperCategoryInsertDTO) {
        return upperCategoryRepository
                .save(upperCategoryInsertDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long upperCategoryUpdate(
            UpperCategoryUpdateDTO upperCategoryUpdateDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateDTO.getName());

        return upperCategoryUpdateDTO.getUpperCategoryNo();
    }

    @Transactional
    public void upperCategoryDelete(
            UpperCategoryDeleteDTO upperCategoryDeleteDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryDeleteDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException());

        upperCategoryRepository.delete(upperCategory);
    }

    @Transactional(readOnly = true)
    public LowerCategoryVO lowerCategorySelect(
            LowerCategorySelectDTO lowerCategorySelectDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategorySelectDTO.getLowerCategoryNo())
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
            LowerCategoryInsertDTO lowerCategoryInsertDTO) {
        return lowerCategoryRepository
                .save(lowerCategoryInsertDTO.toEntity())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long lowerCategoryUpdate(
            LowerCategoryUpdateDTO lowerCategoryUpdateDTO)
            throws IllegalArgumentException {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(lowerCategoryUpdateDTO.getUpperCategory(),
                lowerCategoryUpdateDTO.getName());

        return lowerCategoryUpdateDTO.getLowerCategoryNo();
    }

    @Transactional
    public void lowerCategoryDelete(
            LowerCategoryDeleteDTO lowerCategoryDeleteDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryDeleteDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }

    @Transactional(readOnly = true)
    public BrandVO brandSelect(BrandSelectDTO brandSelectDTO) {
        Brand brand = brandRepository.findById(brandSelectDTO.getBrandNo())
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
    public Long brandInsert(BrandInsertDTO brandInsertDTO) {
        return brandRepository.save(brandInsertDTO.toEntity()).getBrandNo();
    }

    @Transactional
    public Long brandUpdate(BrandUpdateDTO brandUpdateDTO) {
        Brand brand = brandRepository.findById(brandUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        brand.update(brandUpdateDTO.getLowerCategory(), brandUpdateDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void brandDelete(BrandDeleteDTO brandDeleteDTO) {
        Brand brand = brandRepository.findById(brandDeleteDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
