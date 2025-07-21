package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.community.dto.response.CommentSummary;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentSummary> findCommentsByArticleId(Long articleId);
}
