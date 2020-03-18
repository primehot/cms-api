package com.binance.cms.api.service;

import com.binance.cms.api.model.entity.ArticleEntity;

import java.util.UUID;

public interface ArticleService {

    ArticleEntity create(ArticleEntity entity);

    ArticleEntity edit(UUID id, ArticleEntity entity);

    void delete(UUID id);

    ArticleEntity get(UUID id);

    void publish(UUID id);

}
