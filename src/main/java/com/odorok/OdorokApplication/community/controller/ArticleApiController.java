package com.odorok.OdorokApplication.community.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.request.ArticleRegistRequest;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.service.ArticleService;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<ResponseRoot<List<ArticleSummary>>> searchByCondition(ArticleSearchCondition cond){
        return ResponseEntity.ok(CommonResponseBuilder.success("요청 성공",articleService.findByCondition(cond)));
    }
    @PostMapping("")
    public ResponseEntity<ResponseRoot<Void>> registArticle(@RequestPart("data") ArticleRegistRequest request,
                                                                     @RequestPart("images") List<MultipartFile> images,
                                                                     @AuthenticationPrincipal CustomUserDetails user
                                                           ){
        long userId = user.getUser().getId();
        articleService.insertArticle(request,images,userId);
        return ResponseEntity.ok(CommonResponseBuilder.success("글이 성공적으로 등록되었습니다."));
    }
}
