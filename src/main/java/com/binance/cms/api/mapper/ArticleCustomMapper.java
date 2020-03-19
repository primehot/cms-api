package com.binance.cms.api.mapper;

import com.binance.cms.api.model.dto.ArticleCardDto;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.model.entity.ArticleTranslationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleCustomMapper implements Converter<ArticleEntity, ArticleCardDto> {

    @Override
    public ArticleCardDto convert(ArticleEntity source) {
        ArticleCardDto dto = new ArticleCardDto();
        dto.setId(source.getId().toString());
        dto.setIsPublished(source.getIsPublished());
        dto.setImageId(source.getImageId() != null ? source.getImageId().toString() : null);
        ArticleTranslationEntity translation = source.getTranslations().get(0);
        if (translation != null) {
            dto.setTitle(translation.getTitle());
            dto.setDescription(translation.getDescription());
            dto.setLanguage(translation.getLanguage());
        }

        return dto;
    }
}
