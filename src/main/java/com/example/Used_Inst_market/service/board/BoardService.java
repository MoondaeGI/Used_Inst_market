package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromLowerCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.SelectFromUpperCtRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromLowerLoRequestDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpperLoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final PostRepository postRepository;

    private final CategorySelectRepository categorySelectRepository;
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;
    private final BrandRepository brandRepository;

    private final LocalSelectRepository localSelectRepository;
    private final UpperLocalRepository upperLocalRepository;
    private final LowerLocalRepository lowerLocalRepository;

    @Transactional(readOnly = true)
    public List<PostVO> selectAll() {
        return postRepository.findAll().stream()
                .map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperCategorySelect(
            SelectFromUpperCtRequestDTO selectFromUpperCtRequestDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(selectFromUpperCtRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByUpperCategory(upperCategory).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerCategorySelect(
            SelectFromLowerCtRequestDTO selectFromLowerCtRequestDTO) {
        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(selectFromLowerCtRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByLowerCategory(lowerCategory).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> brandSelect(
            SelectFromBrandRequestDTO selectFromBrandRequestDTO) {
        Brand brand = brandRepository
                .findById(selectFromBrandRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return categorySelectRepository.findByBrand(brand).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperLocalSelect(
            SelectFromUpperLoRequestDTO selectFromUpperLoRequestDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(selectFromUpperLoRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByUpperLocal(upperLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerLocalSelect(
            SelectFromLowerLoRequestDTO selectFromLowerLoRequestDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(selectFromLowerLoRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByLowerLocal(lowerLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }
}
