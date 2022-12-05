package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.domain.board.picture.Picture;
import com.example.Used_Inst_market.domain.board.picture.PictureRepository;
import com.example.Used_Inst_market.domain.board.post.Post;
import com.example.Used_Inst_market.domain.board.post.PostRepository;
import com.example.Used_Inst_market.domain.util.FileHandler;
import com.example.Used_Inst_market.web.dto.board.picture.PictureInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PictureVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;
    private final FileHandler fileHandler;

    @Transactional(readOnly = true)
    public List<PictureVO> selectByPost(
            PictureSelectRequestDTO pictureSelectRequestDTO) {
        Post post = postRepository.findById(pictureSelectRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return pictureRepository.findByPost(post)
                .stream()
                .map(PictureVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(PictureInsertRequestDTO pictureInsertRequestDTO)
            throws IOException {
        Post post = postRepository.findById(pictureInsertRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        if(!pictureInsertRequestDTO.getMultipartFiles().isEmpty()) {
            List<Picture> pictures = fileHandler
                    .parsePictureFileInfo(post, pictureInsertRequestDTO.getMultipartFiles());
            pictureRepository.saveAll(pictures);
        }

        return pictureInsertRequestDTO.getPostNo();
    }
/*
    @Transactional
    public Long update(PictureUpdateRequestDTO pictureUpdateRequestDTO) {
        Post post = postRepository.findById(pictureUpdateRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        List<Picture> pictures = pictureRepository.findByPost(post);


    }
 */
}
