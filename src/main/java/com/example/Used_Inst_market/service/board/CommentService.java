package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.comment.Comment;
import com.example.Used_Inst_market.model.domain.board.comment.CommentRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.model.dto.board.comment.CommentInsertDTO;
import com.example.Used_Inst_market.model.dto.board.comment.CommentUpdateDTO;
import com.example.Used_Inst_market.model.vo.board.CommentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CommentVO select(Long commentNo) {
        final Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        return CommentVO.from(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentVO> selectFromPost(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return commentRepository.findByPost(post).stream()
                .map(CommentVO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(CommentInsertDTO commentInsertDTO) {
        final Post post = postRepository.findById(commentInsertDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        final User user = userRepository.findById(commentInsertDTO.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        return commentRepository.save(
                Comment.builder()
                        .post(post)
                        .user(user)
                        .content(commentInsertDTO.getContent())
                        .build())
                .getCommentNo();
    }

    @Transactional
    public Long update(CommentUpdateDTO commentUpdateDTO) {
        final Comment comment = commentRepository.findById(commentUpdateDTO.getCommentNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        comment.update(commentUpdateDTO.getContent());
        return comment.getCommentNo();
    }

    @Transactional
    public void delete(Long commentNo) {
        final Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        commentRepository.delete(comment);
    }
}
