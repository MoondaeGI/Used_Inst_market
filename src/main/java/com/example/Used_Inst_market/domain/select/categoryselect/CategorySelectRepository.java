package com.example.Used_Inst_market.domain.select.categoryselect;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategorySelectRepository extends JpaRepository<CategorySelect, Long> {
    @Query("SELECT categorySelect " +
        "FROM CategorySelect categorySelect " +
        "WHERE categorySelect.post = :post")
    CategorySelect findByPost(@Param("post") Post post);

    @Query("SELECT categorySelect.post " +
            "FROM CategorySelect categorySelect " +
            "WHERE categorySelect.upperCategory = :upperCategory")
    List<Post> findByUpperCategory(@Param("upperCategory") UpperCategory upperCategory);

    @Query("SELECT categorySelect.post " +
            "FROM CategorySelect categorySelect " +
            "WHERE categorySelect.lowerCategory = :lowerCategory")
    List<Post> findByLowerCategory(@Param("lowerCategory") LowerCategory lowerCategory);

    @Query("SELECT categorySelect.post " +
            "FROM CategorySelect categorySelect " +
            "WHERE categorySelect.brand = :brand")
    List<Post> findByBrand(@Param("brand") Brand brand);
}
