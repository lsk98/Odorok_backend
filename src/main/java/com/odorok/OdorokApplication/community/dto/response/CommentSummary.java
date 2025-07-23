package com.odorok.OdorokApplication.community.dto.response;

import com.odorok.OdorokApplication.domain.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSummary {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String nickname;

    @QueryProjection
    public CommentSummary(Long id, String content, LocalDateTime createdAt, String nickname) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.nickname = nickname;
    }
}
