package com.binance.cms.api.service;

import com.binance.cms.api.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageGarbageCollector {

    private final ImageRepository imageRepository;

    //Run ones a day, for remove all not linked images
    @Async
    @Scheduled(fixedRate = 24 * 60 * 60 * 10000)
    public void removeNotLinkedImages() {
        log.info("ImageGarbageCollector start working...");
        imageRepository.deleteAllByIsLinkedAndCreatedAtBefore(false, LocalDateTime.now().minusDays(1));
        log.info("ImageGarbageCollector finished");
    }

}
