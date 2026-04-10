package com.gameportal.specification;

import com.gameportal.model.Game;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class GameSpecification {

    public static Specification<Game> withFilters(String title, String genre, String platform, Integer releaseYear) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                ));
            }

            if (genre != null && !genre.isBlank()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("genre")),
                        genre.toLowerCase()
                ));
            }

            if (platform != null && !platform.isBlank()) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("platform")),
                        platform.toLowerCase()
                ));
            }

            if (releaseYear!= null) {
                predicates.add(criteriaBuilder.equal(root.get("releaseYear"), releaseYear));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
