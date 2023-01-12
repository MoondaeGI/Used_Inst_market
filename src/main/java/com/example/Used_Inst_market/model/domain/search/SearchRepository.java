package com.example.Used_Inst_market.model.domain.search;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {
    Optional<Search> findByPost(Post post);
}
