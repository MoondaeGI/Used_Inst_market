package com.example.Used_Inst_market.service.post;

import com.example.Used_Inst_market.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.web.dto.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.select.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromCityRequestDTO;
import com.example.Used_Inst_market.web.dto.select.localselect.SelectFromLocalRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PostVO;
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
    private final LocalSelectRepository localSelectRepository;

    @Transactional(readOnly = true)
    public PostVO select(@NotNull PostSelectRequestDTO postSelectRequestDTO) {
        Post post = postRepository.findById(postSelectRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return new PostVO(post);
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectAll() {
        return postRepository.findAll().stream()
                .map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(@NotNull PostInsertRequestDTO postInsertRequestDTO) {
        Post post = postRepository.save(postInsertRequestDTO.toEntity());

        categorySelectRepository.save(
                CategorySelect.builder()
                        .post(post)
                        .upperCategory(postInsertRequestDTO.getUpperCategory())
                        .lowerCategory(postInsertRequestDTO.getLowerCategory())
                        .brand(postInsertRequestDTO.getBrand())
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(post)
                        .local(postInsertRequestDTO.getUser().getAddress().getLocal())
                        .city(postInsertRequestDTO.getUser().getAddress().getCity())
                        .build());

        return post.getPostNo();
    }

    @Transactional
    public Long update(@NotNull PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = postRepository.findById(postUpdateRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        CategorySelect categorySelect = categorySelectRepository.findByPost(post);

        post.update(postUpdateRequestDTO.getTitle(), postUpdateRequestDTO.getContent(),
                postUpdateRequestDTO.getPrice(), postUpdateRequestDTO.getSoldYN());

        categorySelect.update(postUpdateRequestDTO.getUpperCategory(),
                postUpdateRequestDTO.getLowerCategory(), postUpdateRequestDTO.getBrand());

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

    @Transactional(readOnly = true)
    public List<PostVO> selectFromLocal(
            SelectFromLocalRequestDTO selectFromLocalRequestDTO) {
        return localSelectRepository
                .findByLocal(selectFromLocalRequestDTO.getLocal())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromCity(
            SelectFromCityRequestDTO selectFromCityRequestDTO) {
        return localSelectRepository
                .findByCity(selectFromCityRequestDTO.getCity())
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }
}