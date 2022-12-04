package com.example.Used_Inst_market.domain.select.localselect;

import com.example.Used_Inst_market.domain.city.City;
import com.example.Used_Inst_market.domain.local.Local;
import com.example.Used_Inst_market.domain.post.Post;
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
            "WHERE localSelect.local = :local")
    List<Post> findByLocal(@Param("local") Local local);

    @Query("SELECT localSelect.post " +
            "FROM LocalSelect localSelect " +
            "WHERE localSelect.city = :city")
    List<Post> findByCity(@Param("city") City city);
}