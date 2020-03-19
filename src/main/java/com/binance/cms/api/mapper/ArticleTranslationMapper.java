package com.binance.cms.api.mapper;

import com.binance.cms.api.model.dto.ArticleTranslationDto;
import com.binance.cms.api.model.entity.ArticleTranslationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;


@Component
public class ArticleTranslationMapper extends AbstractMapper<ArticleTranslationEntity, ArticleTranslationDto> {

    private TypeMap<ArticleTranslationEntity, ArticleTranslationDto> entityToDtoMap;
    private TypeMap<ArticleTranslationDto, ArticleTranslationEntity> dtoToEntityMap;

    public ArticleTranslationMapper(ModelMapper modelMapper) {
        super(modelMapper);
        initMapper();
    }

    private void initMapper() {
        entityToDtoMap = modelMapper.createTypeMap(ArticleTranslationEntity.class, ArticleTranslationDto.class);
        addBaseToDtoMapping(entityToDtoMap);


        dtoToEntityMap = modelMapper.createTypeMap(ArticleTranslationDto.class, ArticleTranslationEntity.class);
        addBaseToEntityMapping(dtoToEntityMap);
    }

    public ArticleTranslationDto toDto(ArticleTranslationEntity entity) {
        return entityToDtoMap.map(entity);
    }

    public ArticleTranslationEntity toEntity(ArticleTranslationDto dto) {
        return dtoToEntityMap.map(dto);
    }
}
