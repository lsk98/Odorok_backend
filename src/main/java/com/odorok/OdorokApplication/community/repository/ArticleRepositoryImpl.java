package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.dto.response.QArticleSummary;
import com.odorok.OdorokApplication.domain.QUser;
import com.odorok.OdorokApplication.draftDomain.QArticle;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QArticle article = QArticle.article;
    private final QUser user = QUser.user;

    @Override
    public List<ArticleSummary> findByCondition(ArticleSearchCondition cond) {
        List<Long> idList = queryFactory
                .select(
                        article.id
                ).from(article)
                .where(
                        categoryEq(cond.getCategory())
                )
                .orderBy(getSortOrder(cond.getSort()))
                .offset((long)(cond.getPageNum()-1)*10)
                .limit(10)
                .fetch();

        List<ArticleSummary> result = queryFactory
                .select(
                        new QArticleSummary(
                                article.id,
                                article.title,
                                article.content,
                                article.createdAt,
                                article.likeCount,
                                article.viewCount,
                                article.commentCount,
                                article.boardType,
                                article.notice,
                                article.vcourseId,
                                article.diseaseId,
                                user.nickname

                        )
                ).from(article)
                .join(user).on(article.userId.eq(user.id))
                .where(article.id.in(idList))
                .orderBy(getSortOrder(cond.getSort()))
                .fetch();

        return result;
    }

    @Nullable
    private BooleanExpression categoryEq(Integer category){
        return article.boardType.eq(category);
    }
    private OrderSpecifier<?> getSortOrder(String sort){
        return switch (sort) {
            case "createdAt" -> article.createdAt.desc();
            case "likeCount" -> article.likeCount.desc();
            case "viewCount" -> article.viewCount.desc();
            default -> article.createdAt.desc();
        };
    }
}
