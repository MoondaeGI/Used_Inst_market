package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.lower.DeleteLowerCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.InsertLowerCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.SelectLowerCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.UpdateLowerCategoryRequestDTO;
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
    public LowerCategoryInfoVO select(SelectLowerCategoryRequestDTO selectLowerCategoryRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(selectLowerCategoryRequestDTO.getLowerCategoryNo())
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
    public Long insert(InsertLowerCategoryRequestDTO insertLowerCategoryRequestDTO) {
        return lowerCategoryRepository
                .save(insertLowerCategoryRequestDTO.toEntity())
                .getLowerCategoryNo();
    }

    @Transactional
    public Long update(UpdateLowerCategoryRequestDTO updateLowerCategoryRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(updateLowerCategoryRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategory.update(updateLowerCategoryRequestDTO.getUpperCategory(),
                updateLowerCategoryRequestDTO.getName());

        return updateLowerCategoryRequestDTO.getLowerCategoryNo();
    }

    @Transactional
    public void delete(DeleteLowerCategoryRequestDTO deleteLowerCategoryRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(deleteLowerCategoryRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        lowerCategoryRepository.delete(lowerCategory);
    }
}
