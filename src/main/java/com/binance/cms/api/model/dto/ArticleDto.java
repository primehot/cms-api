package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ArticleDto extends AbstractDto {

    @NotNull
    private List<ArticleTranslationDto> translations;
}
