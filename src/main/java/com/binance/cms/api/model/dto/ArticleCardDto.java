package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCardDto {

    private String id;
    private String title;
    private String description;
    private String language;
    private Boolean isPublished;
    private String imageId;

}
