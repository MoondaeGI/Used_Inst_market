package com.example.Used_Inst_market.model.domain.select.localselect;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalSelectRepository extends JpaRepository<LocalSelect, Long> {
    @Query("SELECT localSelect " +
            "From LocalSelect localSelect " +
            "WHERE localSelect.post = :post")
    LocalSelect findByPost(@Param("post") Post post);

    @Query("SELECT localSelect.post " +
            "FROM LocalSelect localSelect " +
            "WHERE localSelect.upperLocal = :upperLocal")
    List<Post> findByUpperLocal(@Param("upperLocal") UpperLocal upperLocal);

    @Query("SELECT localSelect.post " +
            "FROM LocalSelect localSelect " +
            "WHERE localSelect.lowerLocal = :lowerLocal")
    List<Post> findByLowerLocal(@Param("lowerLocal") LowerLocal lowerLocal);
}