package com.odorok.OdorokApplication.region.integration;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegionIntegrationTest {

    @LocalServerPort
    private Integer LOCAL_PORT;
}
