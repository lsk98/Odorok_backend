package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;

import java.util.List;

public interface ArticleService {
    public List<ArticleSummary> findByCondition(ArticleSearchCondition condition);

}
