package com.odorok.OdorokApplication.attraction.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttractionIntegrationTest {
    @LocalServerPort
    private Integer LOCAL_PORT;

}
