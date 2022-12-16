package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.picture.PictureRepository;
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
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.web.dto.board.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PictureRepository pictureRepository;

    private final CategorySelectRepository categorySelectRepository;
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;
    private final BrandRepository brandRepository;

    private final LocalSelectRepository localSelectRepository;
    private final UpperLocalRepository upperLocalRepository;
    private final LowerLocalRepository lowerLocalRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostVO select(
            @NotNull PostSelectDTO postSelectDTO) throws IOException {
        Post post = postRepository.findById(postSelectDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return PostVO.from(post);
    }

    @Transactional
    public Long insert(@NotNull PostInsertDTO postInsertDTO) {
        User user = userRepository.findById(postInsertDTO.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        UpperCategory upperCategory = upperCategoryRepository
                .findById(postInsertDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postInsertDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        Brand brand = brandRepository.findById(postInsertDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        UpperLocal upperLocal = upperLocalRepository
                .findById(postInsertDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        LowerLocal lowerLocal = lowerLocalRepository
                .findById(postInsertDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        Post post = postRepository.save(
                Post.builder()
                        .user(user)
                        .title(postInsertDTO.getTitle())
                        .content(postInsertDTO.getContent())
                        .price(postInsertDTO.getPrice())
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
    public Long update(@NotNull PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(postUpdateDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        UpperCategory upperCategory = upperCategoryRepository
                .findById(postUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        Brand brand = brandRepository.findById(postUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        UpperLocal upperLocal = upperLocalRepository
                .findById(postUpdateDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        LowerLocal lowerLocal = lowerLocalRepository
                .findById(postUpdateDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        CategorySelect categorySelect = categorySelectRepository.findByPost(post);
        LocalSelect localSelect = localSelectRepository.findByPost(post);

        post.update(postUpdateDTO.getTitle(), postUpdateDTO.getContent(),
                postUpdateDTO.getPrice(), postUpdateDTO.getSoldYN());
        categorySelect.update(upperCategory, lowerCategory, brand);
        localSelect.update(upperLocal, lowerLocal);

        return postUpdateDTO.getPostNo();
    }

    @Transactional
    public void delete(@NotNull PostDeleteDTO postDeleteDTO) {
        Post post = postRepository.findById(postDeleteDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);
    }
}