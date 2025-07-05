package com.odorok.OdorokApplication.course.integration;

import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.dto.response.holder.CourseResponse;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(CourseIntegrationTest.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void 지역별_코스_조회에_성공한다() {
        String url = UriComponentsBuilder.fromUriString("http://localhost").port(port).path("/api/courses/region")
                .queryParam("sidoCode", "38")
                .queryParam("sigunguCode", "2")
                .queryParam("size", "10")
                .queryParam("page", "0").build().toUri().toString();

        log.debug("요청 url  = {}", url);
        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
        List<CourseSummary> items = (List<CourseSummary>)((LinkedHashMap)response.getData()).get("items");
        assertThat(items.size()).isEqualTo(10);
    }
}


/*

public class PostExample {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.example.com/users";

        // 요청 바디
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Wonjun");
        requestBody.put("email", "wonjun@example.com");

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity에 바디와 헤더를 담아서 전송
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println(response);
    }
}

 */