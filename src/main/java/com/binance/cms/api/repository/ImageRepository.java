package com.binance.cms.api.repository;

import com.binance.cms.api.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

    void deleteAllByIsLinkedAndCreatedAtBefore(Boolean isLinked, LocalDateTime createAtBefore);

}
