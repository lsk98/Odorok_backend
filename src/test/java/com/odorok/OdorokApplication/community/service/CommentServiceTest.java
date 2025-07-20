package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.CommentUpdateRequest;
import com.odorok.OdorokApplication.community.repository.CommentRepository;
import com.odorok.OdorokApplication.domain.Comment;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;
    @InjectMocks
    CommentServiceImpl commentService;

    @Test
    void 댓글_수정_성공(){
        //given
        Long commentId = 1L;
        CommentUpdateRequest request = new CommentUpdateRequest();
        request.setContent("change");
        Comment comment = Comment.builder().id(commentId).content("hi").build();
        //when
        when(commentRepository.findById(commentId)).thenReturn(Optional.ofNullable(comment));
        commentService.updateComment(commentId,request);
        //then
        assertEquals(comment.getContent(),request.getContent());
    }
    @Test
    void 댓글_수정_실패_댓글존재X(){
        Long commentId = 1L;
        CommentUpdateRequest request = new CommentUpdateRequest();
        request.setContent("change");
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->commentService.updateComment(commentId,request));
    }
    @Test
    void 댓글_삭제_성공(){
        //given
        Long commentId = 1L;
        Comment comment = Comment.builder().id(commentId).content("hi").build();
        //when
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        commentService.deleteComment(commentId);
        //then
        verify(commentRepository,times(1)).delete(eq(comment));
    }
    @Test
    void 댓글_삭제_실패_댓글존재X(){
        Long commentId = 1L;
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->commentService.deleteComment(commentId));
    }
}