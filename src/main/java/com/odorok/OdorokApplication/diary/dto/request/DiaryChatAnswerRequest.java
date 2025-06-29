package com.odorok.OdorokApplication.diary.dto.request;

import com.odorok.OdorokApplication.gpt.service.GptService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryChatAnswerRequest {
    private String answer;
    private List<GptService.Prompt> chatLog;
}
