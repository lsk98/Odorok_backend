package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import com.odorok.OdorokApplication.community.repository.PointHistoryRepository;
import com.odorok.OdorokApplication.community.repository.ProfileRepository;
import com.odorok.OdorokApplication.domain.PointHistory;
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
    private final PointHistoryRepository pointHistoryRepository;
    @Override
    @Transactional
    public void insertArticleTransactional(Article article, List<String> urls,Long userId){
        articleRepository.save(article);
        Long articleId = article.getId();
        //url리스트 db작업
        articleImageService.insertArticleImageUrl(articleId,urls);
        //유저 프로필 조회
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("점수 추가 실패"));
        profile.setMileage(profile.getMileage()+30);
        profile.setActivityPoint(profile.getActivityPoint()+30);
        //점수 이력 추가
        PointHistory history = PointHistory.builder().articleId(articleId).userId(article.getUserId()).point(30).build();
        pointHistoryRepository.save(history);
    }
}
