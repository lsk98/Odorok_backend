package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleDetail;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.dto.response.QArticleSummary;
import com.odorok.OdorokApplication.domain.QUser;
import com.odorok.OdorokApplication.draftDomain.Article;
import com.odorok.OdorokApplication.draftDomain.QArticle;
import com.odorok.OdorokApplication.draftDomain.QProfile;
import com.odorok.OdorokApplication.draftDomain.QTier;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QArticle article = QArticle.article;
    private final QUser user = QUser.user;
    private final QTier tier = QTier.tier;
    private final QProfile profile = QProfile.profile;

    @Override
    public List<ArticleSummary> findByCondition(ArticleSearchCondition cond) {
        List<Long> idList = queryFactory
                .select(
                        article.id
                ).from(article)
                .where(
                        titleLike(cond.getTitle())
                )
                .orderBy(getSortOrder(cond.getSort()))
                .offset((long) (cond.getPageNum() - 1) * 50)
                .limit(50)
                .fetch();

        List<ArticleSummary> result = queryFactory
                .select(
                        new QArticleSummary(
                                article.id,
                                article.title,
                                article.createdAt,
                                article.likeCount,
                                article.viewCount,
                                article.commentCount,
                                article.boardType,
                                article.notice,
                                user.nickname
                        )
                ).from(article)
                .join(user).on(article.userId.eq(user.id))
                .where(article.id.in(idList))
                .orderBy(getSortOrder(cond.getSort()))
                .fetch();

        return result;
    }
    @Override
    public ArticleDetail findArticleDetailById(Long articleId) {
        ArticleDetail articleDetail = queryFactory.select(Projections.constructor(ArticleDetail.class,
                article.id,article.title,article.content,article.createdAt,article.likeCount,article.viewCount,
                article.commentCount,article.notice,article.userId,user.nickname,tier.title)).from(article)
                .join(user).on(article.userId.eq(user.id))
                .join(profile).on(article.userId.eq(profile.userId))
                .join(tier).on(profile.tierId.eq(tier.id)).where(article.id.eq(articleId)).fetchOne();
        return articleDetail;
    }


    @Nullable
    private BooleanExpression titleLike(String title) {
        return StringUtils.hasText(title) ? article.title.like("%" + title + "%") : null;
    }

    private OrderSpecifier<?> getSortOrder(String sort) {
        return switch (sort) {
            case "createdAt" -> article.createdAt.desc();
            case "likeCount" -> article.likeCount.desc();
            case "viewCount" -> article.viewCount.desc();
            default -> article.createdAt.desc();
        };
    }
}
