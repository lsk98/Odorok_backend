package com.odorok.OdorokApplication.community.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.odorok.OdorokApplication.community.dto.response.QArticleSummary is a Querydsl Projection type for ArticleSummary
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QArticleSummary extends ConstructorExpression<ArticleSummary> {

    private static final long serialVersionUID = 578842090L;

    public QArticleSummary(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<Integer> likeCount, com.querydsl.core.types.Expression<Integer> viewCount, com.querydsl.core.types.Expression<Integer> commentCount, com.querydsl.core.types.Expression<Integer> boardType, com.querydsl.core.types.Expression<Boolean> notice, com.querydsl.core.types.Expression<Long> vcourseId, com.querydsl.core.types.Expression<Integer> diseaseId, com.querydsl.core.types.Expression<String> nickname) {
        super(ArticleSummary.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, int.class, int.class, int.class, int.class, boolean.class, long.class, int.class, String.class}, id, title, content, createdAt, likeCount, viewCount, commentCount, boardType, notice, vcourseId, diseaseId, nickname);
    }

}

