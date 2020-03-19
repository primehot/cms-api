package com.binance.cms.api.repository;

import com.binance.cms.api.model.entity.ArticleEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, UUID> {
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"translations"})
    Optional<ArticleEntity> getById(UUID id);
}
