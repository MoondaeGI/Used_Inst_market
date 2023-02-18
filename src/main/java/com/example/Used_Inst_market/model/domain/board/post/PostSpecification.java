package com.example.Used_Inst_market.model.domain.board.post;

import com.example.Used_Inst_market.model.domain.search.Search;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;

public class PostSpecification {
    public static Specification<Post> postSearch(Map<String, Object> searchKey) {
        return (Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (searchKey.isEmpty()) return null;

            Join<Post, Search> postSearchJoin = root.join("search", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            for (String key : searchKey.keySet()) {
                switch (key) {
                    case "minPrice":
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), (Integer) searchKey.get(key)));
                        break;
                    case "maxPrice":
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), (Integer) searchKey.get(key)));
                        break;
                    case "title":
                    case "content":
                        predicates.add(criteriaBuilder.like(root.get(key), "%" + searchKey.get(key) + "%"));
                        break;
                    default:
                        predicates.add(criteriaBuilder.equal(postSearchJoin.get(key), searchKey.get(key)));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
