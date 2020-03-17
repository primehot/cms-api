package com.binance.cms.api.mapper;

import com.binance.cms.api.model.dto.ArticleDto;
import com.binance.cms.api.model.dto.ArticleTranslationDto;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.model.entity.ArticleTranslationEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ArticleMapper extends AbstractMapper<ArticleEntity, ArticleDto> {

    private TypeMap<ArticleEntity, ArticleDto> entityToDtoMap;
    private TypeMap<ArticleDto, ArticleEntity> dtoToEntityMap;

    private Converter<List<ArticleTranslationEntity>, List<ArticleTranslationDto>> toTranslationsDto;
    private Converter<List<ArticleTranslationDto>, List<ArticleTranslationEntity>> toTranslationsEntity;

    public ArticleMapper(ModelMapper modelMapper, ArticleTranslationMapper articleTranslationMapper) {
        super(modelMapper);

        this.toTranslationsDto = ctx -> ctx.getSource() == null ? null :
                ctx.getSource().stream().map(articleTranslationMapper::toDto).collect(Collectors.toList());
        this.toTranslationsEntity = ctx -> ctx.getSource() == null ? null :
                ctx.getSource().stream().map(articleTranslationMapper::toEntity).collect(Collectors.toList());

        initMapper();
    }


    private void initMapper() {
        entityToDtoMap = modelMapper.createTypeMap(ArticleEntity.class, ArticleDto.class);
        addBaseToDtoMapping(entityToDtoMap);
        entityToDtoMap.addMappings(mapper -> mapper.using(toTranslationsDto).map(ArticleEntity::getTranslations, ArticleDto::setTranslations));

        dtoToEntityMap = modelMapper.createTypeMap(ArticleDto.class, ArticleEntity.class);
        addBaseToEntityMapping(dtoToEntityMap);
        dtoToEntityMap.addMappings(mapper -> mapper.using(toTranslationsEntity).map(ArticleDto::getTranslations, ArticleEntity::setTranslations));
    }

    public ArticleDto toDto(ArticleEntity entity) {
        return entityToDtoMap.map(entity);
    }

    public ArticleEntity toEntity(ArticleDto dto) {
        return dtoToEntityMap.map(dto);
    }
}
