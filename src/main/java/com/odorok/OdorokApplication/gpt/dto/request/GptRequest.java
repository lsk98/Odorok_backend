package com.odorok.OdorokApplication.gpt.dto.request;

import com.odorok.OdorokApplication.gpt.service.GptService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GptRequest {
    String model;
    List<GptService.Prompt> messages;
    Double temperature;
}
