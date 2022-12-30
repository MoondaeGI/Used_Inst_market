package com.example.Used_Inst_market.model.domain.category.lower;

import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LowerCategoryRepository extends JpaRepository<LowerCategory, Long> {
    List<LowerCategory> findByUpperCategory(UpperCategory upperCategory);
}
