package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategorySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.category.LowerCategoryInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LowerCategoryService {
    private final LowerCategoryRepository lowerCategoryRepository;

    @Transactional
    public LowerCategoryInfoVO select(LowerCategorySelectRequestDTO lowerCategorySelectRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategorySelectRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new LowerCategoryInfoVO(lowerCategory);
    }

    @Transactional
    public List<LowerCategoryInfoVO> selectAll() {
        return lowerCategoryRepository.findAll().stream()
                .map(LowerCategoryInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO) {
        return lowerCategoryRepository
                .save(lowerCategoryInsertRequestDTO.toEntity())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long update(LowerCategoryUpdateRequestDTO lowerCategoryUpdateRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryUpdateRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(lowerCategoryUpdateRequestDTO.getUpperCategory(),
                lowerCategoryUpdateRequestDTO.getName());

        return lowerCategoryUpdateRequestDTO.getLowerCategoryNo();
    }

    @Transactional
    public void delete(LowerCategoryDeleteRequestDTO lowerCategoryDeleteRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(lowerCategoryDeleteRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }
}
