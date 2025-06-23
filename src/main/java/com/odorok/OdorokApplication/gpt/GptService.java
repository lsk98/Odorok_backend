package com.odorok.OdorokApplication.gpt;

import com.nimbusds.openid.connect.sdk.Prompt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface GptService {
    String sendPrompt(String context, Prompt prompt);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class Prompt { // 프롬프트 생성을 위한 인터페이스 : role 은 system(설정용 프롬프트), user(사용자 프롬프트), agent(gpt)
        String role;
        String prompt;
        LocalDateTime date;
    }
}
