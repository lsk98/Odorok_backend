package com.odorok.OdorokApplication.gpt.dto.response;

import com.odorok.OdorokApplication.gpt.dto.response.nested.Choice;
import com.odorok.OdorokApplication.gpt.dto.response.nested.Usage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GptResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String serviceTier;
    private String systemFingerprint;
}

/*
    {
        "id": "chatcmpl-BlXvrgZC0vcvwpouuVju0jq2o4MzX",
        "object": "chat.completion",
        "created": 1750671551,
        "model": "gpt-4.1-2025-04-14",
        "choices": [
            {
                "index": 0,
                "message": {
                    "role": "assistant",
                    "content": "안녕하세요! 저는 OpenAI에서 개발한 인공지능 언어 모델, ChatGPT입니다. 다양한 주제에 대해 대화하고, 질문에 답변해드리며, 글쓰기, 번역, 정보 제공 등 여러 가지 작업을 도와드릴 수 있습니다. 언제든 궁금한 점이 있거나 도움이 필요하시면 편하게 말씀해 주세요!",
                    "refusal": null,
                    "annotations": []
                },
                "logprobs": null,
                "finish_reason": "stop"
            }
        ],
        "usage": {
            "prompt_tokens": 12,
            "completion_tokens": 77,
            "total_tokens": 89,
            "prompt_tokens_details": {
                "cached_tokens": 0,
                "audio_tokens": 0
            },
            "completion_tokens_details": {
                "reasoning_tokens": 0,
                "audio_tokens": 0,
                "accepted_prediction_tokens": 0,
                "rejected_prediction_tokens": 0
            }
        },
        "service_tier": "default",
        "system_fingerprint": "fp_51e1070cf2"
    }
    """
*/
