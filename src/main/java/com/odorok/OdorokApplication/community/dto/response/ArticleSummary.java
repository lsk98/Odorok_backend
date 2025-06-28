package com.odorok.OdorokApplication.community.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class ArticleSummary {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Integer boardType;
    private Boolean notice;
    private Long vcourseId;
    private Long diseaseId;
    private String nickname;

    @QueryProjection
    public ArticleSummary(Long id, String title, String content, LocalDateTime createdAt,
                          Integer likeCount, Integer viewCount, Integer commentCount,
                          Integer boardType, Boolean notice,
                          Long vcourseId, Long diseaseId, String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.boardType = boardType;
        this.notice = notice;
        this.vcourseId = vcourseId;
        this.diseaseId = diseaseId;
        this.nickname = nickname;
    }
}
