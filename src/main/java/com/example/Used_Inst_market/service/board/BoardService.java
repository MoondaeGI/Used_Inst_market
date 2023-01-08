package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.post.Post;
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
import com.example.Used_Inst_market.model.dto.board.post.PostSelectFromPriceDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.model.vo.category.CategorySelectVO;
import com.example.Used_Inst_market.model.vo.local.LocalSelectVO;
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
    public List<PostVO> selectFromTitle(String keyword) {
        return postRepository
                .findByTitleContaining(keyword)
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromContent(String keyword) {
        return postRepository
                .findByContentContaining(keyword)
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromTitleOrContent(String keyword) {
        return postRepository
                .findByTitleOrContent(keyword)
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromPrice(PostSelectFromPriceDTO postSelectFromPriceDTO) {
        Integer lessPrice = postSelectFromPriceDTO.getLessPrice() * 10000;
        Integer greaterPrice = postSelectFromPriceDTO.getGreaterPrice() * 10000;

        return postRepository
                .findByPriceLessThanEqualAndPriceGreaterThanEqual(lessPrice, greaterPrice)
                .stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategorySelectVO categorySelectFromPost(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        return CategorySelectVO.from(categorySelectRepository.findByPost(post));
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperCategorySelect(Long upperCategoryNo) {
        UpperCategory upperCategory = upperCategoryRepository.findById(upperCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByUpperCategory(upperCategory).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerCategorySelect(Long lowerCategoryNo) {
        LowerCategory lowerCategory = lowerCategoryRepository.findById(lowerCategoryNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByLowerCategory(lowerCategory).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> brandSelect(Long brandNo) {
        Brand brand = brandRepository.findById(brandNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return categorySelectRepository.findByBrand(brand).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocalSelectVO localSelectFromPost(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return LocalSelectVO.from(localSelectRepository.findByPost(post));
    }

    @Transactional(readOnly = true)
    public List<PostVO> upperLocalSelect(Long upperLocalNo) {
        final UpperLocal upperLocal = upperLocalRepository.findById(upperLocalNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByUpperLocal(upperLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> lowerLocalSelect(Long lowerLocalNo) {
        final LowerLocal lowerLocal = lowerLocalRepository.findById(lowerLocalNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByLowerLocal(lowerLocal).stream()
                .map(PostVO::from)
                .collect(Collectors.toList());
    }
}
