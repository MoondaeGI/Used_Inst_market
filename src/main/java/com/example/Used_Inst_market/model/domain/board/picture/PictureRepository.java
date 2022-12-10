package com.example.Used_Inst_market.model.domain.board.picture;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findByPost(Post post);
}
