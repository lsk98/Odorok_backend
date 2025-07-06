//package com.odorok.OdorokApplication.gpt.service;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class GptServiceImplTest {
//    @Autowired
//    GptService gptService;
//
////    @Disabled
////    @ParameterizedTest
////    @ValueSource(strings = {"한국어로 자기소개 부탁합니다."})
////    public void GPT_API_호출_테스트_GPT로부터_응답이_온다(String prompt) {
////        GptService.Prompt newPrompt = new GptService.Prompt("system", prompt);
////        List<GptService.Prompt> context = gptService.sendPrompt(null, newPrompt);
////
////        System.out.println(context.toString());
////    }
//}