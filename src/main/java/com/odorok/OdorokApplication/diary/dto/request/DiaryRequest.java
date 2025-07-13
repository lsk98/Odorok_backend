package com.odorok.OdorokApplication.diary.dto.request;

import lombok.Getter;

@Getter
public class DiaryRequest {
    private Long vcourseId;
    private String title;
    private String content;
}
