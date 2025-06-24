package com.odorok.OdorokApplication.gpt.service;

import lombok.*;
import java.util.List;

public interface GptService {
    List<Prompt> sendPrompt(List<Prompt> context, Prompt prompt);
    // content : 지금까지 이루어진 대화내역.
    // prompt : 새로 전달할 대화 내용.
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class Prompt { // 프롬프트 생성을 위한 인터페이스 : role 은 system(설정용 프롬프트), user(사용자 프롬프트), agent(gpt)
        String role;
        String content;

        @Override
        public String toString() {
            return String.format("{ \"role\" : \"%s\", \"content\" : \"%s\"}"
                    , this.role, this.content);
        }
    }
}
