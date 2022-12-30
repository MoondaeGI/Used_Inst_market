package com.example.Used_Inst_market.model.domain.category.brand;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByLowerCategory(LowerCategory lowerCategory);
}
