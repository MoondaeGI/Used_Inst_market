package com.example.Used_Inst_market.model.domain.board.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyWord);
    List<Post> findByContentContaining(String keyWord);
    List<Post> findByPriceLessThanEqualAndPriceGreaterThanEqual(
            Integer lessPrice, Integer greaterPrice);

    @Query("SELECT post " +
            "FROM Post post " +
            "WHERE post.title LIKE '%:keyword%' " +
            "OR post.content LIKE '%:keyword%'")
    List<Post> findByTitleOrContent(@Param("keyword") String keyword);
}
