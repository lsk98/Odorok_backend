package com.odorok.OdorokApplication.commons;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
// WebClient 활용하여 Rest api 호출하는 모듈
public class RestApiCallerImpl implements RestApiCaller {
    @Override
    public String doGet(Map<String, String> parameters) {
        WebClient.builder().baseUrl("http://www.data.go.kr");

        return "";
    }

    @Override
    public String doPost(Map<String, String> parameters, String content) {
        return "";
    }
}
