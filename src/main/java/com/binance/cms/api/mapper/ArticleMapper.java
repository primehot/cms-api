package com.binance.cms.api.mapper;

import com.binance.cms.api.model.dto.ArticleDto;
import com.binance.cms.api.model.entity.ArticleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;


@Component
public class ArticleMapper extends AbstractMapper<ArticleEntity, ArticleDto> {

    private TypeMap<ArticleEntity, ArticleDto> entityToDtoMap;
    private TypeMap<ArticleDto, ArticleEntity> dtoToEntityMap;

    public ArticleMapper(ModelMapper modelMapper) {
        super(modelMapper);
        initMapper();
    }

    private void initMapper() {
        entityToDtoMap = modelMapper.createTypeMap(ArticleEntity.class, ArticleDto.class);
        addBaseToDtoMapping(entityToDtoMap);


        dtoToEntityMap = modelMapper.createTypeMap(ArticleDto.class, ArticleEntity.class);
        addBaseToEntityMapping(dtoToEntityMap);
    }

    public ArticleDto toDto(ArticleEntity entity) {
        return entityToDtoMap.map(entity);
    }

    public ArticleEntity toEntity(ArticleDto dto) {
        return dtoToEntityMap.map(dto);
    }
}
