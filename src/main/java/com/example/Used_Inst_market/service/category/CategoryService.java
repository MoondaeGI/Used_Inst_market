package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;

    @Transactional(readOnly = true)
    public UpperCategoryVO upperCategorySelect(
            UpperCategorySelectRequestDTO upperCategorySelectRequestDTO)
            throws IllegalArgumentException {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategorySelectRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new UpperCategoryVO(upperCategory);
    }

    @Transactional(readOnly = true)
    public List<UpperCategoryVO> upperCategorySelectAll() {
        return upperCategoryRepository.findAll().stream()
                .map(UpperCategoryVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public LowerCategoryVO lowerCategorySelect(
            LowerCategorySelectRequestDTO lowerCategorySelectRequestDTO)
            throws IllegalArgumentException {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategorySelectRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new LowerCategoryVO(lowerCategory);
    }

    @Transactional
    public List<LowerCategoryVO> lowerCategorySelectAll() {
        return lowerCategoryRepository.findAll().stream()
                .map(LowerCategoryVO::new)
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
    public Long lowerCategoryInsert(
            LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO) {
        return lowerCategoryRepository
                .save(lowerCategoryInsertRequestDTO.toEntity())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long upperCategoryUpdate(
            UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO)
            throws IllegalArgumentException {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateRequestDTO.getName());

        return upperCategoryUpdateRequestDTO.getUpperCategoryNo();
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
    public void upperCategoryDelete(
            UpperCategoryDeleteRequestDTO upperCategoryDeleteRequestDTO)
            throws IllegalArgumentException {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryDeleteRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException());

        upperCategoryRepository.delete(upperCategory);
    }

    @Transactional
    public void lowerCategoryDelete(
            LowerCategoryDeleteRequestDTO lowerCategoryDeleteRequestDTO)
            throws IllegalArgumentException {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryDeleteRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }
}
