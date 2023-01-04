package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
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
import com.example.Used_Inst_market.model.dto.board.post.*;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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
    public PostVO select(final PostSelectDTO postSelectDTO) throws IOException {
        final Post post = postRepository.findById(postSelectDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return PostVO.from(post);
    }

    @Transactional
    public Long insert(final PostInsertDTO postInsertDTO) {
        final User user = userRepository.findById(postInsertDTO.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        final UpperCategory upperCategory = upperCategoryRepository
                .findById(postInsertDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postInsertDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final Brand brand = brandRepository.findById(postInsertDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        final UpperLocal upperLocal = upperLocalRepository
                .findById(postInsertDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        final LowerLocal lowerLocal = lowerLocalRepository
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
    public Long update(final PostUpdateDTO postUpdateDTO) {
        final Post post = postRepository.findById(postUpdateDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        final UpperCategory upperCategory = upperCategoryRepository
                .findById(postUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final Brand brand = brandRepository.findById(postUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        final UpperLocal upperLocal = upperLocalRepository
                .findById(postUpdateDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        final LowerLocal lowerLocal = lowerLocalRepository
                .findById(postUpdateDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        final CategorySelect categorySelect = categorySelectRepository.findByPost(post);
        final LocalSelect localSelect = localSelectRepository.findByPost(post);

        post.update(postUpdateDTO.getTitle(), postUpdateDTO.getContent(),
                postUpdateDTO.getPrice(), postUpdateDTO.getSoldYN());
        categorySelect.update(upperCategory, lowerCategory, brand);
        localSelect.update(upperLocal, lowerLocal);

        return postUpdateDTO.getPostNo();
    }

    @Transactional
    public Long updateSoldYN(final PostUpdateSoldYNDTO postUpdateSoldYNDTO) {
        final Post post = postRepository.findById(postUpdateSoldYNDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if(post.getSoldYN() == SoldYN.SALE) {
            post.updateSoldYN(SoldYN.SOLD_OUT);
        } else {
            post.updateSoldYN(SoldYN.SALE);
        }

        return postUpdateSoldYNDTO.getPostNo();
    }

    @Transactional
    public void delete(final PostDeleteDTO postDeleteDTO) {
        final Post post = postRepository.findById(postDeleteDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);
    }
}