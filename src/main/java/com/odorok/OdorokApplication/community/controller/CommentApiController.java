package com.odorok.OdorokApplication.community.controller;

import com.odorok.OdorokApplication.commons.aop.annotation.CheckCommentOwner;
import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.community.dto.request.CommentUpdateRequest;
import com.odorok.OdorokApplication.community.service.CommentService;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PutMapping("/{comment-id}")
    @CheckCommentOwner(commentId = "commentId")
    public ResponseEntity<ResponseRoot<Void>> updateComment(@PathVariable("comment-id") Long commentId,
                                                            @RequestBody CommentUpdateRequest request
    ){
        commentService.updateComment(commentId,request);
        return ResponseEntity.ok(CommonResponseBuilder.success("댓글 수정 성공"));
    }

    @DeleteMapping("/{comment-id}")
    @CheckCommentOwner(commentId = "commentId")
    public ResponseEntity<ResponseRoot<Void>> deleteComment(@PathVariable("comment-id") Long commentId
    ){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(CommonResponseBuilder.success("댓글 삭제 성공"));
    }
}
