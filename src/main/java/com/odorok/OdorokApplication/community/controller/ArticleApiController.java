package com.odorok.OdorokApplication.community.controller;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleServiceImpl articleService;

    @GetMapping("")
    public ResponseEntity<List<ArticleSummary>> searchByCondition(ArticleSearchCondition cond){

        return ResponseEntity.ok(articleService.findByCondition(cond));
    }
}
