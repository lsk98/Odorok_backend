package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.ArticleUpdateRequest;
import com.odorok.OdorokApplication.draftDomain.Article;

import java.util.List;

public interface ArticleTransactionService {
    public void insertArticleTransactional(Article article, List<String> urls,Long userId);
    public List<String> updateArticleInfo(ArticleUpdateRequest request,List<String> newUrlList,Long articleId);
}
