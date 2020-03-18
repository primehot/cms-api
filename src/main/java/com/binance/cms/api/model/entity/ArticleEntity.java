package com.binance.cms.api.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity extends AbstractEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private List<ArticleTranslationEntity> translations;

    @Column
    private UUID imageId;

    @Column
    private Boolean isPublished;

    @Column
    private LocalDateTime publishedAt;

}
