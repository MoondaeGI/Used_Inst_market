package com.example.Used_Inst_market.model.domain.board.post;

import com.example.Used_Inst_market.model.domain.search.Search;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostSpecification {
    public static Specification<Post> postSearch(Map<String, Object> searchKey) {
        return (root, query, criteriaBuilder) -> {
            Join<Post, Search> postSearchJoin = root.join("post", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            for (String key : searchKey.keySet()) {
                if (key.equals("minPrice")) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(postSearchJoin.get(key), (Integer) searchKey.get(key)));
                }
                else if (key.equals("maxPrice")) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(postSearchJoin.get(key), (Integer) searchKey.get(key)));
                }
                else if (key.equals("title") || key.equals("content")) {
                    predicates.add(criteriaBuilder.like(postSearchJoin.get(key), "%" + searchKey.get(key) + "%"));
                }
                else {
                    predicates.add(criteriaBuilder.equal(postSearchJoin.get(key), searchKey.get(key)));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
