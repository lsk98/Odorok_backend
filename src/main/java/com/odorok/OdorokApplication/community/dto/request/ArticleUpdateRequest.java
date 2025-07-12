package com.odorok.OdorokApplication.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateRequest {
    private String title;
    private String content;
    private Integer boardType;
    private Boolean notice;
    private Integer diseaseId;
    private Long courseId;
}
