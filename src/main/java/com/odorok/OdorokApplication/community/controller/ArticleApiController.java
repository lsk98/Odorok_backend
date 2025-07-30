package com.odorok.OdorokApplication.community.controller;

import com.odorok.OdorokApplication.commons.aop.annotation.CheckArticleOwner;
import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.request.ArticleRegistRequest;
import com.odorok.OdorokApplication.community.dto.request.ArticleUpdateRequest;
import com.odorok.OdorokApplication.community.dto.request.CommentRegistRequest;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.dto.response.CommentSummary;
import com.odorok.OdorokApplication.community.service.ArticleService;
import com.odorok.OdorokApplication.draftDomain.Article;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.odorok.OdorokApplication.domain.QUser.user;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    @GetMapping("/search")
    public ResponseEntity<ResponseRoot<List<ArticleSummary>>> searchByCondition(ArticleSearchCondition cond) {
        return ResponseEntity.ok(CommonResponseBuilder.success("요청 성공", articleService.findByCondition(cond)));
    }

    @PostMapping("")
    public ResponseEntity<ResponseRoot<Void>> registArticle(@RequestPart("data") ArticleRegistRequest request,
                                                            @RequestPart("images") List<MultipartFile> images,
                                                            @AuthenticationPrincipal CustomUserDetails user) {
        long userId = user.getUser().getId();
        articleService.insertArticle(request, images, userId);
        return ResponseEntity.ok(CommonResponseBuilder.success("게시물이 성공적으로 등록되었습니다."));
    }

    @GetMapping("/{articles-id}")
    public ResponseEntity<ResponseRoot<Article>> searchArticleDetail(@PathVariable("articles-id") Long articleId) {
        Article article = articleService.findByArticleId(articleId);
        return ResponseEntity.ok(CommonResponseBuilder.success("게시물 조회 성공", article));
    }

    @DeleteMapping("/{articles-id}")
    public ResponseEntity<ResponseRoot<Void>> deleteArticle(@PathVariable("articles-id") Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(CommonResponseBuilder.success("게시물이 성공적으로 삭제되었습니다."));
    }

    @PutMapping("/{articles-id}")
    public ResponseEntity<ResponseRoot<Void>> updateArticle(@RequestPart(name = "data") ArticleUpdateRequest request,
                                                            @RequestPart(name = "images") List<MultipartFile> images,
                                                            @PathVariable("articles-id") Long articleId,
                                                            @AuthenticationPrincipal CustomUserDetails user
                                                            ) {
        Long userId = user.getUser().getId();
        articleService.updateArticle(request,images,articleId,userId);
        return ResponseEntity.ok(CommonResponseBuilder.success("게시물이 성공적으로 수정되었습니다."));
    }
    @PostMapping("/{articles-id}/likes")
    public ResponseEntity<ResponseRoot<Void>> updateArticleLike(@PathVariable("articles-id") Long articleId,
                                                            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUser().getId();
        articleService.updateLike(articleId,userId);
        return ResponseEntity.ok(CommonResponseBuilder.success("좋아요가 등록되었습니다."));
    }
    @GetMapping("/{articles-id}/comments")
    public ResponseEntity<ResponseRoot<List<CommentSummary>>> searchComments(@PathVariable("articles-id") Long articleId
    ) {
        List<CommentSummary> result = articleService.findCommentsByArticleId(articleId);
        return ResponseEntity.ok(CommonResponseBuilder.success("댓글을 성공적으로 불러왔습니다.",result));
    }
    @PostMapping("/{articles-id}/comments")
    public ResponseEntity<ResponseRoot<Void>> registComment(@PathVariable("articles-id") Long articleId,
                                                           @AuthenticationPrincipal CustomUserDetails user,
                                                           @RequestBody CommentRegistRequest request
    ) {
        Long userId = user.getUser().getId();
        articleService.registComment(articleId,request,userId);
        return ResponseEntity.ok(CommonResponseBuilder.success("댓글 작성 성공"));
    }
}
