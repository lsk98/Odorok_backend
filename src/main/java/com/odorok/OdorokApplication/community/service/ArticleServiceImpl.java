package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleImageService articleImageService;
    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleSummary> findByCondition(ArticleSearchCondition condition) {
        return articleRepository.findByCondition(condition);
    }
}
