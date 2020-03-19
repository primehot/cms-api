package com.binance.cms.api.service;

import com.binance.cms.api.exception.ImageNotFoundException;
import com.binance.cms.api.exception.ItemNotFoundException;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.model.entity.ImageEntity;
import com.binance.cms.api.repository.ArticleRepository;
import com.binance.cms.api.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;
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
        log.info("Article edit. ID {}", id.toString());
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
        ArticleEntity found = repository.getById(id).orElseThrow(ItemNotFoundException::new);
        log.info("Article retrieve. ID {}", found.getId());
        return found;
    }

    @Override
    public void publish(UUID id) {
        ArticleEntity toPublish = repository.findById(id).orElseThrow(ItemNotFoundException::new);

        //Simulation on publishing event
        toPublish.setIsPublished(true);
        toPublish.setPublishedAt(LocalDateTime.now().plusSeconds(3));
        repository.save(toPublish);
        log.info("Article published. ID {}", id);
    }

    @Override
    public Page<ArticleEntity> getAll(Pageable pageable) {
        Page<ArticleEntity> articles = repository.findAll(pageable);
        log.info("Articles retrieve");

        return articles;
    }

    private void setImageLink(UUID imageId, boolean isLinked) {
        if (imageId != null) {
            ImageEntity image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
            image.setIsLinked(isLinked);
            imageRepository.save(image);
        }
    }
}
