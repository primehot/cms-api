package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ArticleDto extends AbstractDto {

    private List<ArticleTranslationDto> translations;

    private String imageId;
}
