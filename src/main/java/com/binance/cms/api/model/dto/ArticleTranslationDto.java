package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleTranslationDto extends AbstractDto {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String language;

}
