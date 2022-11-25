package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.upper.DeleteUpperCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.InsertUpperCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.SelectUpperCategoryRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpdateUpperCategoryRequestDTO;
import com.example.Used_Inst_market.web.vo.category.UpperCategoryInfoVO;
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
    public UpperCategoryInfoVO select(SelectUpperCategoryRequestDTO selectUpperCategoryRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(selectUpperCategoryRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new UpperCategoryInfoVO(upperCategory);
    }

    @Transactional
    public List<UpperCategoryInfoVO> selectAll() {
        return upperCategoryRepository.findAll().stream()
                .map(UpperCategoryInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(InsertUpperCategoryRequestDTO insertUpperCategoryRequestDTO) {
        return upperCategoryRepository
                .save(insertUpperCategoryRequestDTO.toEntity())
                .getUpperCategoryNo();
    }

    @Transactional
    public Long update(UpdateUpperCategoryRequestDTO updateUpperCategoryRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(updateUpperCategoryRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        upperCategory.update(updateUpperCategoryRequestDTO.getName());

        return updateUpperCategoryRequestDTO.getUpperCategoryNo();
    }

    @Transactional
    public void delete(DeleteUpperCategoryRequestDTO deleteUpperCategoryRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(deleteUpperCategoryRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException());

        upperCategoryRepository.delete(upperCategory);
    }
}
