package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromCityRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromLocalRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    private final CategorySelectRepository categorySelectRepository;
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;
    private final BrandRepository brandRepository;

    private final LocalSelectRepository localSelectRepository;
    private final UpperLocalRepository upperLocalRepository;
    private final LowerLocalRepository lowerLocalRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostVO select(@NotNull PostSelectRequestDTO postSelectRequestDTO) {
        Post post = postRepository.findById(postSelectRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return PostVO.of(post, postSelectRequestDTO.getPictures());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectAll() {
        return postRepository.findAll().stream()
                .map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(@NotNull PostInsertRequestDTO postInsertRequestDTO) {
        User user = userRepository.findById(postInsertRequestDTO.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        UpperCategory upperCategory = upperCategoryRepository
                .findById(postInsertRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postInsertRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        Brand brand = brandRepository.findById(postInsertRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        UpperLocal upperLocal = upperLocalRepository
                .findById(postInsertRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        LowerLocal lowerLocal = lowerLocalRepository
                .findById(postInsertRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        Post post = postRepository.save(
                Post.builder()
                        .user(user)
                        .title(postInsertRequestDTO.getTitle())
                        .content(postInsertRequestDTO.getContent())
                        .price(postInsertRequestDTO.getPrice())
                        .build());

        categorySelectRepository.save(
                CategorySelect.builder()
                        .post(post)
                        .upperCategory(upperCategory)
                        .lowerCategory(lowerCategory)
                        .brand(brand)
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(post)
                        .upperLocal(upperLocal)
                        .lowerLocal(lowerLocal)
                        .build());

        return post.getPostNo();
    }

    @Transactional
    public Long update(@NotNull PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = postRepository.findById(postUpdateRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        UpperCategory upperCategory = upperCategoryRepository
                .findById(postUpdateRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postUpdateRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        Brand brand = brandRepository.findById(postUpdateRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        UpperLocal upperLocal = upperLocalRepository
                .findById(postUpdateRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        LowerLocal lowerLocal = lowerLocalRepository
                .findById(postUpdateRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        CategorySelect categorySelect = categorySelectRepository.findByPost(post);
        LocalSelect localSelect = localSelectRepository.findByPost(post);

        post.update(postUpdateRequestDTO.getTitle(), postUpdateRequestDTO.getContent(),
                postUpdateRequestDTO.getPrice(), postUpdateRequestDTO.getSoldYN());

        categorySelect.update(upperCategory, lowerCategory, brand);
        localSelect.update(upperLocal, lowerLocal);

        return postUpdateRequestDTO.getPostNo();
    }

    @Transactional
    public void delete(@NotNull PostDeleteRequestDTO postDeleteRequestDTO) {
        Post post = postRepository.findById(postDeleteRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromUpperCategory(
            SelectFromUpperRequestDTO selectFromUpperRequestDTO) {

        UpperCategory upperCategory = upperCategoryRepository
                .findById(selectFromUpperRequestDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByUpperCategory(upperCategory)
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromLowerCategory(
            SelectFromLowerRequestDTO selectFromLowerRequestDTO) {

        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(selectFromLowerRequestDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return categorySelectRepository
                .findByLowerCategory(lowerCategory)
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromBrand(
            SelectFromBrandRequestDTO selectFromBrandRequestDTO) {

        Brand brand = brandRepository
                .findById(selectFromBrandRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return categorySelectRepository
                .findByBrand(brand)
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromLocal(
            SelectFromLocalRequestDTO selectFromLocalRequestDTO) {

        UpperLocal upperLocal = upperLocalRepository
                .findById(selectFromLocalRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        return localSelectRepository
                .findByUpperLocal(upperLocal)
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromCity(
            SelectFromCityRequestDTO selectFromCityRequestDTO) {

        LowerLocal lowerLocal = lowerLocalRepository
                .findById(selectFromCityRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        return localSelectRepository
                .findByLowerLocal(lowerLocal)
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }
}