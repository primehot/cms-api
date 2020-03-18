package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDto extends AbstractDto {

    private List<ArticleTranslationDto> translations;

    private Boolean isPublished;

    private String publishedAt;

    private String imageId;
}
