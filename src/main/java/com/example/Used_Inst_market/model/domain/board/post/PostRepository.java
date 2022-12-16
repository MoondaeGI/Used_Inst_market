package com.example.Used_Inst_market.model.domain.board.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyWord);
    List<Post> findByContentContaining(String keyWord);
    List<Post> findByTitleContainingOrContentContaining(String keyWord);
    List<Post> findByPriceLessThanEqualAndPriceGreaterThanEqual(
            Integer lessPrice, Integer greaterPrice);
}
