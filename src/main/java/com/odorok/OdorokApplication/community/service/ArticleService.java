package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.request.ArticleRegistRequest;
import com.odorok.OdorokApplication.community.dto.request.ArticleUpdateRequest;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.draftDomain.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    public List<ArticleSummary> findByCondition(ArticleSearchCondition condition);
    public void insertArticle(ArticleRegistRequest request, List<MultipartFile> images, Long userId);
    public Article findByArticleId(Long articleId);
    public void deleteArticle(Long articleId);
    public void updateArticle(ArticleUpdateRequest request, List<MultipartFile> images, Long articleId,Long userId);
}
