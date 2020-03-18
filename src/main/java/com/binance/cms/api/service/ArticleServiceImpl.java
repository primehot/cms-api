package com.binance.cms.api.service;

import com.binance.cms.api.exception.ImageNotFoundException;
import com.binance.cms.api.exception.ItemNotFoundException;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.model.entity.ImageEntity;
import com.binance.cms.api.repository.ArticleRepository;
import com.binance.cms.api.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository repository;
    private final ImageRepository imageRepository;

    @Override
    public ArticleEntity create(ArticleEntity entity) {
        ArticleEntity result = repository.save(entity);
        setImageLink(entity.getImageId(), true);
        log.info("Article created with ID {}", result.getId().toString());
        return result;
    }

    @Override
    public ArticleEntity edit(UUID id, ArticleEntity entity) {
        ArticleEntity old = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        setImageLink(old.getImageId(), false);
        setImageLink(entity.getImageId(), true);

        entity.setId(id);
        ArticleEntity updated = repository.save(entity);
        log.info("offer edit. ID {}", id.toString());
        return updated;
    }

    @Override
    public void delete(UUID id) {
        ArticleEntity toRemove = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(toRemove);
        log.info("Article deleted. ID {}", id.toString());
    }

    @Override
    public ArticleEntity get(UUID id) {
        ArticleEntity found = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        log.info("Article retrieve. ID {}", found.getId());
        return found;
    }

    private void setImageLink(UUID imageId, boolean isLinked) {
        if (imageId != null) {
            ImageEntity image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
            image.setIsLinked(isLinked);
            imageRepository.save(image);
        }
    }
}
