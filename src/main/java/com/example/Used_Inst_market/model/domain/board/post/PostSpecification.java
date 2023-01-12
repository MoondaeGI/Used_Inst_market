package com.example.Used_Inst_market.model.domain.board.post;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.search.Search;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

public class PostSpecification {
    public static Specification<Post> postFindAll() {
        return (root, query, criteriaBuilder) -> {
            Join<Search, Post> posts = root.join("post", JoinType.INNER);
            return (Predicate) criteriaBuilder;
        };
    }

    public static Specification<Post> matchUpperCategory(UpperCategory upperCategory) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("upperCategory"), upperCategory));
    }

    public static Specification<Post> matchLowerCategory(LowerCategory lowerCategory) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lowerCategory"), lowerCategory));
    }

    public static Specification<Post> matchBrand(Brand brand) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brand)));
    }

    public static Specification<Post> matchUpperLocal(UpperLocal upperLocal) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("upperLocal"), upperLocal)));
    }

    public static Specification<Post> matchLowerLocal(LowerLocal lowerLocal) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lowerLocal"), lowerLocal)));
    }

    public static Specification<Post> likeTitle(String title) {
        return (((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%")));
    }

    public static Specification<Post> likeContent(String content) {
        return (((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("content"), "%" + content + "%")));
    }

    public static Specification<Post> greaterThanMinPrice(Integer minPrice) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice)));
    }

    public static Specification<Post> lessThanMaxPrice(Integer maxPrice) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice)));
    }
}
