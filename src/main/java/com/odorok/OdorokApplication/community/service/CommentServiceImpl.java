package com.odorok.OdorokApplication.community.service;


import com.odorok.OdorokApplication.commons.aop.annotation.CheckCommentOwner;
import com.odorok.OdorokApplication.community.dto.request.CommentUpdateRequest;
import com.odorok.OdorokApplication.community.repository.CommentRepository;
import com.odorok.OdorokApplication.domain.Comment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    @CheckCommentOwner(commentId = "commentId")
    public void updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new EntityNotFoundException("수정 실패 해당 댓글 없음"));
        comment.setContent(request.getContent());
    }

    @Override
    @Transactional
    @CheckCommentOwner(commentId = "commentId")
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("삭제 실패 해당 댓글 없음"));
        commentRepository.delete(comment);
    }
}
