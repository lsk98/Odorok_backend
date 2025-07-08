package com.odorok.OdorokApplication.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRegistRequest {
    private String title;
    private String content;
    private Integer boardType;
    private Boolean notice;
    private Long diseaseId;
    private Long courseId;
}
