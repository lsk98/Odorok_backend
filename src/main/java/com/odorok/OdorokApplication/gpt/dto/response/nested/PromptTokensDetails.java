package com.odorok.OdorokApplication.gpt.dto.response.nested;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromptTokensDetails {
    private int cachedTokens;
    private int audioTokens;
}
