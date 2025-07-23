package com.odorok.OdorokApplication.community.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter//테스트 위해 생성
@NoArgsConstructor
public class ArticleUpdateRequest {
    private String title;
    private String content;
    private Integer boardType;
    private Boolean notice;
    private Integer diseaseId;
    private Long courseId;
}
