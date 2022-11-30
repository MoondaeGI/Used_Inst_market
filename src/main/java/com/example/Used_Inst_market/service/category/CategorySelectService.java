package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectedAsBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectedAsLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectedAsUpperRequestDTO;
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
    public List<PostVO> selectedAsUpperCategory(
            SelectedAsUpperRequestDTO selectedAsUpperRequestDTO) {
        return categorySelectRepository
                .findByUpperCategory(selectedAsUpperRequestDTO.getUpperCategory())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectedAsLowerCategory(
            SelectedAsLowerRequestDTO selectedAsLowerRequestDTO) {
        return categorySelectRepository
                .findByLowerCategory(selectedAsLowerRequestDTO.getLowerCategory())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectedAsBrand(
            SelectedAsBrandRequestDTO selectedAsBrandRequestDTO) {
        return categorySelectRepository
                .findByBrand(selectedAsBrandRequestDTO.getBrand())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }
}
