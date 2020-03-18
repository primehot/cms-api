package com.binance.cms.api.controller;

import com.binance.cms.api.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.CacheControl.noCache;
import static org.springframework.http.MediaType.IMAGE_JPEG;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping(path = "/{id}")
    public ResponseEntity getImage(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        byte[] image = imageService.get(uuid);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(noCache().getHeaderValue());

        return ResponseEntity.ok().headers(headers)
                .contentType(IMAGE_JPEG)
                .body(image);
    }

    @PostMapping(path = "/upload")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile image) {
        UUID imageId = imageService.save(image);
        return ResponseEntity.ok().body(imageId);
    }

}
