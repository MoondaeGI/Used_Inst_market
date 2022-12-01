package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategorySelectService {
    private final CategorySelectRepository categorySelectRepository;

    @Transactional(readOnly = true)
    public List<PostVO> selectFromUpperCategory(
            SelectFromUpperRequestDTO selectFromUpperRequestDTO) {
        return categorySelectRepository
                .findByUpperCategory(selectFromUpperRequestDTO.getUpperCategory())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromLowerCategory(
            SelectFromLowerRequestDTO selectFromLowerRequestDTO) {
        return categorySelectRepository
                .findByLowerCategory(selectFromLowerRequestDTO.getLowerCategory())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromBrand(
            SelectFromBrandRequestDTO selectFromBrandRequestDTO) {
        return categorySelectRepository
                .findByBrand(selectFromBrandRequestDTO.getBrand())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }
}
