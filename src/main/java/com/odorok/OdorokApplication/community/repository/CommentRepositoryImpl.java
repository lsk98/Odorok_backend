package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.community.dto.response.CommentSummary;
import com.odorok.OdorokApplication.community.dto.response.QCommentSummary;
import com.odorok.OdorokApplication.domain.QComment;
import com.odorok.OdorokApplication.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;
    private final QUser user = QUser.user;;
    @Override
    public List<CommentSummary> findCommentsByArticleId(Long articleId) {
        List<Long> commentIdList = queryFactory
                .select(comment.id)
                .from(comment)
                .where(comment.articleId.eq(articleId))
                .fetch();
        List<CommentSummary> commentList = queryFactory
                .select(new QCommentSummary(
                        comment.id,
                        comment.content,
                        comment.createdAt,
                        user.nickname
                ))
                .from(comment)
                .join(user).on(comment.userId.eq(user.id))
                .where(comment.id.in(commentIdList))
                .orderBy(comment.createdAt.asc())
                .fetch();
        return commentList;
    }
}
