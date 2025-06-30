package com.odorok.OdorokApplication.diary.dto.response;

import com.odorok.OdorokApplication.gpt.service.GptService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryChatResponse {
    private String content;
    private List<GptService.Prompt> chatLog;
}
