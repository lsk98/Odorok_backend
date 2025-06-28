package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import com.odorok.OdorokApplication.community.repository.ProfileRepository;
import com.odorok.OdorokApplication.draftDomain.Article;
import com.odorok.OdorokApplication.draftDomain.Profile;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleTransactionServiceImpl implements ArticleTransactionService{

    private final ArticleRepository articleRepository;
    private final ArticleImageService articleImageService;
    private final ProfileRepository profileRepository;
    @Override
    @Transactional
    public void insertArticleTransactional(Article article, List<String> urls,Long userId){
        articleRepository.save(article);
        Long articleId = article.getId();
        articleImageService.insertArticleImageUrl(articleId,urls);
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("프로필을 찾을 수 없습니다."));
        profile.setMileage(profile.getMileage()+30);
        profile.setActivityPoint(profile.getActivityPoint()+30);
    }
}
