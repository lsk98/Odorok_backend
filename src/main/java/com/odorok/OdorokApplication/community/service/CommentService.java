package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.CommentUpdateRequest;

public interface CommentService {
    public void updateComment(Long commentId,CommentUpdateRequest request);
    public void deleteComment(Long commentId);
}
