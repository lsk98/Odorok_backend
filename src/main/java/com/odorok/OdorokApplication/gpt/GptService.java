package com.odorok.OdorokApplication.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface GptService {
    String sendMessage(String context, Message newMessage);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class Message {
        String role;
        String message;
        LocalDateTime date;
    }
}
