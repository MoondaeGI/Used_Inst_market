package com.example.Used_Inst_market.service.post;

import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.web.dto.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostVO select(PostSelectRequestDTO postSelectRequestDTO)
            throws IllegalArgumentException {
        Post post = postRepository.findById(postSelectRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return new PostVO(post);
    }

    @Transactional
    public List<PostVO> selectAll() {
        return postRepository.findAll().stream()
                .map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(PostInsertRequestDTO postInsertRequestDTO) {
        return postRepository.save(postInsertRequestDTO.toEntity()).getPostNo();
    }

    @Transactional
    public Long update(PostUpdateRequestDTO postUpdateRequestDTO)
            throws IllegalArgumentException {
        Post post = postRepository.findById(postUpdateRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        post.update(postUpdateRequestDTO.getTitle(), postUpdateRequestDTO.getContent(),
                postUpdateRequestDTO.getPrice(), postUpdateRequestDTO.getSoldYN());

        return postUpdateRequestDTO.getPostNo();
    }

    @Transactional
    public void delete(PostDeleteRequestDTO postDeleteRequestDTO)
            throws IllegalArgumentException {
        Post post = postRepository.findById(postDeleteRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);
    }
}
