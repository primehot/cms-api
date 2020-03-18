package com.binance.cms.api.controller;

import com.binance.cms.api.mapper.ArticleMapper;
import com.binance.cms.api.model.dto.ArticleDto;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private ArticleMapper articleMapper;
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody @Valid ArticleDto dto) {
        ArticleEntity entity = articleMapper.toEntity(dto);
        ArticleEntity result = articleService.create(entity);

        return ResponseEntity.ok(articleMapper.toDto(result));
    }

}
