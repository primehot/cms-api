package com.binance.cms.api.repository;

import com.binance.cms.api.model.entity.ArticleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, UUID> {
}
