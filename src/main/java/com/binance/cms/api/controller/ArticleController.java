package com.binance.cms.api.controller;

import com.binance.cms.api.mapper.ArticleCustomMapper;
import com.binance.cms.api.mapper.ArticleMapper;
import com.binance.cms.api.model.dto.ArticleDto;
import com.binance.cms.api.model.entity.ArticleEntity;
import com.binance.cms.api.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private ArticleMapper articleMapper;
    private ArticleService articleService;
    private ArticleCustomMapper articleCustomMapper;

    @PostMapping
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody @Valid ArticleDto dto) {
        ArticleEntity entity = articleMapper.toEntity(dto);
        ArticleEntity result = articleService.create(entity);

        return ResponseEntity.ok(articleMapper.toDto(result));
    }

    @GetMapping
    public ResponseEntity getAll(@PageableDefault Pageable pageable) {
        Page<ArticleEntity> result = articleService.getAll(pageable);

        return ResponseEntity.ok().body(result.map(articleCustomMapper::convert));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") String id) {
        UUID uuidId = UUID.fromString(id);
        ArticleEntity result = articleService.get(uuidId);

        return ResponseEntity.ok(articleMapper.toDto(result));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ArticleDto> editArticle(@PathVariable("id") String id, @RequestBody @Valid ArticleDto dto) {
        UUID uuidId = UUID.fromString(id);
        ArticleEntity result = articleService.edit(uuidId, articleMapper.toEntity(dto));

        return ResponseEntity.ok(articleMapper.toDto(result));
    }

    @PostMapping(path = "/{id}/publish")
    public ResponseEntity publishArticle(@PathVariable("id") String id) {
        UUID uuidId = UUID.fromString(id);
        articleService.publish(uuidId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteArticle(@PathVariable("id") String id) {
        UUID uuidId = UUID.fromString(id);
        articleService.delete(uuidId);

        return ResponseEntity.ok().build();
    }

}
