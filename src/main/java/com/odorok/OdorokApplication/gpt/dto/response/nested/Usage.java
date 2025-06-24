package com.odorok.OdorokApplication.gpt.dto.response.nested;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usage {
    private int promptTokens;
    private int completionTokens;
    private PromptTokensDetails promptTokensDetails;
    private CompletionTokensDetails completionTokensDetails;
}
