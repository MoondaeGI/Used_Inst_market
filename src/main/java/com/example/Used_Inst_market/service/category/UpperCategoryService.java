package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.category.UpperCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UpperCategoryService {
    private final UpperCategoryRepository upperCategoryRepository;

    @Transactional
    public UpperCategoryVO select(UpperCategorySelectRequestDTO upperCategorySelectRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategorySelectRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new UpperCategoryVO(upperCategory);
    }

    @Transactional
    public List<UpperCategoryVO> selectAll() {
        return upperCategoryRepository.findAll().stream()
                .map(UpperCategoryVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(UpperCategoryInsertRequestDTO upperCategoryInsertRequestDTO) {
        return upperCategoryRepository
                .save(upperCategoryInsertRequestDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long update(UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryUpdateRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(upperCategoryUpdateRequestDTO.getName());

        return upperCategoryUpdateRequestDTO.getUpperCategoryNo();
    }

    @Transactional
    public void delete(UpperCategoryDeleteRequestDTO upperCategoryDeleteRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(upperCategoryDeleteRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException());

        upperCategoryRepository.delete(upperCategory);
    }
}
