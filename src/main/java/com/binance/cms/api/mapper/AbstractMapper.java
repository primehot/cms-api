package com.binance.cms.api.mapper;

import com.binance.cms.api.model.dto.AbstractDto;
import com.binance.cms.api.model.entity.AbstractEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.binance.cms.api.util.DateUtil.formatDate;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> {

    protected ModelMapper modelMapper;

    public AbstractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected Converter<UUID, String> uuidToString = ctx -> ctx.getSource() == null ? null : ctx.getSource().toString();
    protected Converter<String, UUID> stringToUuid = ctx -> ctx.getSource() == null ? null : UUID.fromString(ctx.getSource());

    protected Converter<LocalDateTime, String> localDateTimeToString = ctx -> ctx.getSource() == null ? null : formatDate(ctx.getSource());
    protected Converter<String, LocalDateTime> stringToLocalDateTime = ctx -> ctx.getSource() == null ? null : formatDate(ctx.getSource());

    protected void addBaseToDtoMapping(TypeMap<? extends AbstractEntity, ? extends AbstractDto> typeMap) {
        typeMap.addMappings(mapper -> {
            mapper.using(uuidToString).map(AbstractEntity::getId, AbstractDto::setId);
            mapper.using(localDateTimeToString).map(AbstractEntity::getCreatedAt, AbstractDto::setCreatedAt);
            mapper.using(localDateTimeToString).map(AbstractEntity::getModifiedAt, AbstractDto::setModifiedAt);
        });
    }

    protected void addBaseToEntityMapping(TypeMap<? extends AbstractDto, ? extends AbstractEntity> typeMap) {
        typeMap.addMappings(mapper -> {
            mapper.using(stringToUuid).map(AbstractDto::getId, AbstractEntity::setId);
            mapper.using(stringToLocalDateTime).map(AbstractDto::getCreatedAt, AbstractEntity::setCreatedAt);
            mapper.using(stringToLocalDateTime).map(AbstractDto::getModifiedAt, AbstractEntity::setModifiedAt);
        });
    }

    public abstract D toDto(E entity);

    public abstract E toEntity(D dto);
}
