package com.binance.cms.api.mapper.image;

import com.binance.cms.api.exception.FileConvertException;
import com.binance.cms.api.model.entity.ImageEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.binance.cms.api.util.ByteUtil.convertBytes;

@Component
public class ImageMapper implements Converter<MultipartFile, ImageEntity> {

    @Override
    public ImageEntity convert(MultipartFile image) {
        try {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setBytes(convertBytes(image.getBytes()));
            return imageEntity;
        } catch (IOException exception) {
            throw new FileConvertException(exception);
        }
    }
}
