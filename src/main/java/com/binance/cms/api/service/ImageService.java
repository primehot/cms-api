package com.binance.cms.api.service;

import com.binance.cms.api.exception.ImageNotFoundException;
import com.binance.cms.api.exception.InvalidImageException;
import com.binance.cms.api.mapper.image.ImageMapper;
import com.binance.cms.api.model.entity.ImageEntity;
import com.binance.cms.api.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

import static com.binance.cms.api.util.ByteUtil.convertBytes;
import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final ImageMapper fileMapper;

    public byte[] get(UUID id) {
        Optional<ImageEntity> foundImages = imageRepository.findById(id);
        if (foundImages.isPresent()) {
            return convertBytes(foundImages.get().getBytes());
        }
        throw new ImageNotFoundException(String.format("Image %s not found", id.toString()));
    }

    public UUID save(MultipartFile image) {
        if (image.isEmpty()) {
            throw new InvalidImageException("Image could not be empty");
        } else {
            ImageEntity imageEntity = fileMapper.convert(image);
            return imageRepository.save(requireNonNull(imageEntity)).getId();
        }
    }

}
