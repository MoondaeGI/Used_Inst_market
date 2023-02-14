package com.example.Used_Inst_market.model.domain.category.lower;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LowerCategoryRepository extends JpaRepository<LowerCategory, Long> {
    @Query("SELECT lowerCategory " +
            "FROM LowerCategory lowerCategory " +
            "WHERE lowerCategory.upperCategory = :upperCategory")
    List<LowerCategory> findByUpperCategory(@Param("upperCategory") UpperCategory upperCategory);
}
