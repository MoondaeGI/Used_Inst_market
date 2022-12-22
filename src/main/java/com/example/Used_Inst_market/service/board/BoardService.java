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
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromContentDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromPriceDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromTitleOrContentDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectFromTitleDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromBrandDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromLoCategoryDTO;
import com.example.Used_Inst_market.web.dto.board.select.categoryselect.PostSelectFromUpCategoryDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.PostSelectFromLoLocalDTO;
import com.example.Used_Inst_market.web.dto.board.select.localselect.SelectFromUpLocalDTO;
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
    public List<PostVO> selectFromTitle(
            final PostSelectFromTitleDTO postSelectFromTitleDTO) {
        return postRepository
                .findByTitleContaining(postSelectFromTitleDTO.getKeyword())
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromContent(
            final PostSelectFromContentDTO postSelectFromContentDTO) {
        return postRepository
                .findByContentContaining(postSelectFromContentDTO.getKeyword())
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromTitleOrContent(
            final PostSelectFromTitleOrContentDTO postSelectFromTitleOrContentDTO) {
        return postRepository
                .findByTitleOrContent(
                        postSelectFromTitleOrContentDTO.getKeyword())
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromPrice(
            final PostSelectFromPriceDTO postSelectFromPriceDTO) {
        Integer lessPrice = postSelectFromPriceDTO.getLessPrice() * 10000;
        Integer greaterPrice = postSelectFromPriceDTO.getGreaterPrice() * 10000;

        return postRepository
                .findByPriceLessThanEqualAndPriceGreaterThanEqual(
                        lessPrice, greaterPrice)
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperCategorySelect(
            final PostSelectFromUpCategoryDTO postSelectFromUpCategoryDTO) {
        UpperCategory upperCategory = upperCategoryRepository
                .findById(postSelectFromUpCategoryDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByUpperCategory(upperCategory).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerCategorySelect(
            final PostSelectFromLoCategoryDTO selectFromLowerCtRequestDTO) {
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
            final PostSelectFromBrandDTO postSelectFromBrandDTO) {
        Brand brand = brandRepository
                .findById(postSelectFromBrandDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return categorySelectRepository.findByBrand(brand).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperLocalSelect(
            final SelectFromUpLocalDTO selectFromUpLocalDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(selectFromUpLocalDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByUpperLocal(upperLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerLocalSelect(
            final PostSelectFromLoLocalDTO postSelectFromLoLocalDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(postSelectFromLoLocalDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByLowerLocal(lowerLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }
}
