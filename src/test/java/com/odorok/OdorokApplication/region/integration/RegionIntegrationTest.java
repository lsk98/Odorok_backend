package com.odorok.OdorokApplication.region.integration;

import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.infrastructures.domain.Sigungu;
import com.odorok.OdorokApplication.region.dto.response.item.SidoSummary;
import com.odorok.OdorokApplication.region.dto.response.item.SigunguSummary;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegionIntegrationTest {

    @LocalServerPort
    private Integer LOCAL_PORT;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 시도코드_조회에_성공한다() {
        String url = UriComponentsBuilder.fromUriString("http://localhost").port(LOCAL_PORT).path("/api/regions/sido")
                .build().toUriString();
        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
        List<SidoSummary> sidos = (List<SidoSummary>)(((LinkedHashMap)root.getData()).get("items"));

        assertThat(sidos).isNotNull();
        assertThat(sidos.size()).isNotZero();
    }

    @Test
    public void 시군구코드_조회에_성공한다() {
        String url = UriComponentsBuilder.fromUriString("http://localhost").port(LOCAL_PORT).path("/api/regions/sigungu")
                .queryParam("sidoCode", 6)
                .build().toUriString();
        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
        List<SigunguSummary> sigungus = (List<SigunguSummary>)(((LinkedHashMap)root.getData()).get("items"));

        assertThat(sigungus).isNotNull();
        assertThat(sigungus.size()).isNotZero();
    }

    @Test
    public void 시군구코드_조회에_실패한다() {
        String url = UriComponentsBuilder.fromUriString("http://localhost").port(LOCAL_PORT).path("/api/regions/sigungu")
                .queryParam("sidoCode", 1)
                .build().toUriString();
        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
        assertThat(root.getData()).isNull();
    }
}
