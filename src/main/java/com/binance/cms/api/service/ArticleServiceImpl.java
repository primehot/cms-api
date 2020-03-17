package com.binance.cms.api.service;

import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository repository;

    @Override
    public ArticleEntity save(ArticleEntity entity) {
        ArticleEntity result = repository.save(entity);

        log.info("Article was saved with id: " + result.getId());
        return result;
    }
}
