package com.odorok.OdorokApplication.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleSearchCondition {
    private int pageNum;
    private String sort;
    private String title;
}