package com.binance.cms.api.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "article_translation")
public class ArticleTranslationEntity extends AbstractEntity {

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String language;

}
