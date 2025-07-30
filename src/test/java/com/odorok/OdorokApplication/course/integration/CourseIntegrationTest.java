package com.odorok.OdorokApplication.course.integration;

import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.dto.request.CourseScheduleRequest;
import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.dto.response.item.DiseaseAndCourses;
import com.odorok.OdorokApplication.course.dto.response.item.RecommendedCourseSummary;
import com.odorok.OdorokApplication.course.repository.*;
import com.odorok.OdorokApplication.course.repository.customed.CourseRepositoryCustom;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.course.service.ScheduledAttractionServiceImpl;
import com.odorok.OdorokApplication.diary.repository.VisitedCourseRepository;
import com.odorok.OdorokApplication.domain.ScheduledCourse;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.security.dto.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(CourseIntegrationTest.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VisitedCourseRepository visitedCourseRepository;

    @Autowired
    private UserDiseaseRepository userDiseaseRepository;

    @Autowired
    private ScheduledCourseRepository scheduledCourseRepository;

    @Autowired
    private ScheduledAttractionRepository scheduledAttractionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @LocalServerPort
    private int port;
    private static final String COMMON_URL = "http://localhost";
    private static final String COMMON_PATH = "/api/courses";
    private static final Long COURSE_ID = 1L;

    private static final String TEST_USER_EMAIL = "jihun@example.com";
    private static final Long TEST_USER_ID = 1L;

    @BeforeEach
    public void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                new CustomUserDetails(
                        User.builder().email(TEST_USER_EMAIL).id(TEST_USER_ID).role("USER").build()),
                null,
                List.of(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

//    @Test
//    public void 지역별_코스_조회에_성공한다() {
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path(COMMON_PATH+"/region")
//                .queryParam("sidoCode", "38")
//                .queryParam("sigunguCode", "2")
//                .queryParam("size", "10")
//                .queryParam("page", "0").build().toUri().toString();
//
//        log.debug("요청 url  = {}", url);
//        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
//        List<CourseSummary> items = (List<CourseSummary>)((LinkedHashMap)response.getData()).get("items");
////        assertThat(items.size()).isEqualTo(10);
////        assertThat(response.getStatus()).isEqualTo("success");
//    }

    @Test
    public void 지역별_코스_조회에_실패한다() {
        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path(COMMON_PATH+"/region")
                .queryParam("sidoCode", "1")
                .queryParam("sigunguCode", "2")
                .queryParam("size", "10")
                .queryParam("page", "0").build().toUri().toString();

        log.debug("요청 url  = {}", url);
        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
//        assertThat(response.getData()).isNull();
//        assertThat(response.getMessage().startsWith("유효하지 않은 시도 코드 입니다.")).isTrue();
//        assertThat(response.getStatus()).isEqualTo("fail");
    }

//    @Test
//    public void 전체_코스_조회에_성공한다() {
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path(COMMON_PATH)
//                .queryParam("size", "10")
//                .queryParam("page", "0").build().toUri().toString();
//
//        log.debug("요청 url  = {}", url);
//        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
//        List<CourseSummary> items = (List<CourseSummary>)((LinkedHashMap)response.getData()).get("items");
////        assertThat(items.size()).isEqualTo(10);
//    }

    @Test
    public void 코스_상세_조회에_성공한다() {
        String url = UriComponentsBuilder.fromUriString(COMMON_URL)
                .port(port).path(COMMON_PATH+"/detail").queryParam("courseId", COURSE_ID).toUriString();

        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
        LinkedHashMap detail = (LinkedHashMap)(response.getData());

        assertThat(detail).isNotNull();
        System.out.println(detail);
    }

    @Test
//    @Sql("/sql/test-vcourse.sql")
    @Sql(statements = {"delete from visited_courses where review = 'review'",
            "delete from user_diseases", "delete from health_infos"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void TOP_코스_조회에_성공한다() {
        String url = UriComponentsBuilder.fromUriString(COMMON_URL)
                .port(port).path(COMMON_PATH+"/top").toUriString();

        ResponseRoot response = restTemplate.getForObject(url, ResponseRoot.class);
        LinkedHashMap detail = (LinkedHashMap)(response.getData());

        List<RecommendedCourseSummary> topStars = (List< RecommendedCourseSummary>)detail.get("topStars");
        List<RecommendedCourseSummary> topVisited = (List< RecommendedCourseSummary>)detail.get("topVisited");
        List<RecommendedCourseSummary> topReviewCount = (List< RecommendedCourseSummary>)detail.get("topReviewCount");

        assertThat(detail).isNotNull();
        assertThat(topStars).isNotNull();
        assertThat(topVisited).isNotNull();
        assertThat(topReviewCount).isNotNull();
        System.out.println(detail);
    }

    // jwt가 없는 상태에선 필터링 된다.
//    @Test
////    @Sql("/sql/disease_course_test.sql")
//    @Sql(statements = {"delete from visited_courses where review = 'review'",
//    "delete from user_diseases", "delete from health_infos"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void 질병_코스_조회에_성공한다() {
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path(COMMON_PATH+"/disease").toUriString();
//        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
//        LinkedHashMap data = (LinkedHashMap) root.getData();
//        List<DiseaseAndCourses> diseaseAndCourses = (List<DiseaseAndCourses>)data.get("items");
//
////        assertThat(diseaseAndCourses).isNotEmpty();
//    }

//    @Test
//    @Sql(statements = "insert into profiles(user_id, sido_code, sigungu_code) values(1, 1, 2)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "delete from profiles where user_id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void 코스_없는_지역_회원_주소지_근방의_코스_조회에_성공한다() {
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path("/api/courses/user-region").toUriString();
//        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
//        LinkedHashMap data = (LinkedHashMap) root.getData();
//        List<CourseSummary> courses = (List<CourseSummary>)data.get("items");
//
////        assertThat(courses).isEmpty();
//        System.out.println(courses);
//    }

//    @Test
//    @Sql(statements = "insert into profiles(user_id, sido_code, sigungu_code) values(1, 6, 10)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "delete from profiles where user_id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void 코스_있는_지역_회원_주소지_근방의_코스_조회에_성공한다() {
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL).port(port).path(COMMON_PATH+"/user-region").toUriString();
//        ResponseRoot root = restTemplate.getForObject(url, ResponseRoot.class);
//        LinkedHashMap data = (LinkedHashMap) root.getData();
//        List<CourseSummary> courses = (List<CourseSummary>)data.get("items");
//
////        assertThat(courses).isNotEmpty();
//        System.out.println(courses);
//    }

    @Test
    @Transactional
    public void 방문예정_조회에_성공한다() {

    }


    // 아래 테스트는 Repeatable 하지 못한 관계로 새롭게 작성하여야 함.
//    @Test
//    public void 방문코스_등록에_성공한다() {
//
//        CourseScheduleRequest request = new CourseScheduleRequest();
//        request.setEmail("jihun@example.com");
//        request.setCourseId(1L);
//        request.setDueDate(LocalDateTime.now());
//        request.setAttractionIds(List.of(56644l, 56645l, 56646l));
//        String url = UriComponentsBuilder.fromUriString(COMMON_URL)
//                        .port(port).path(COMMON_PATH+"/schedule").toUriString();
//
//        restTemplate.postForObject(url, request, Void.class);
//        assertThat(scheduledCourseRepository.findByUserId(1L).size()).isOne();
//        Long scId = scheduledCourseRepository.findByUserId(1L).get(0).getId();
//        assertThat(scheduledAttractionRepository.findByScourseId(scId).size()).isEqualTo(3);
//
//        scheduledAttractionRepository.deleteByScourseId(scId);
//        scheduledCourseRepository.deleteById(scId);
//    }

    /*
    신규 부하 테스트.
    테스트 내용 : 5명의 질병별 코스 추천을 10000번 수행함.
    예상 결과 : 뷰를 사용한 방식의 연산이 더 빠르다.
     */
    @Nested
    class LoadTester{
        @Autowired
        private CourseQueryService courseQueryService;

        @Test
        @Transactional
//        @Sql("/sql/disease_course_test.sql")
        public void 질병_코스_메서드가_성공한다() {
            List<DiseaseAndCourses> res = courseQueryService.queryCoursesForDiseasesOf(1L,
                    CourseQueryService.RecommendationCriteria.STARS, Pageable.ofSize(10));
            System.out.println(res);
//            assertThat(res).isNotEmpty();
        }
//        @Test
//        @Transactional
//        @Sql("/sql/disease_course_test.sql")
//        public void 질병_코스_부하_테스트_뷰() {
//            Runtime runtime = Runtime.getRuntime();
//            runtime.gc(); // GC 유도
//
//            long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
//            long start = System.currentTimeMillis();
//            for(int i = 0; i < 1000; i++) {
//                for(long u = 1l; u <= 5l; u++) {
//                    List<DiseaseAndCourses> diseaseAndCourses = courseQueryService.queryCoursesForDiseasesOf(u,
//                            CourseQueryService.RecommendationCriteria.STARS);
//                }
//            }
//            // execution time : 459205
//            long afterUsedMem = runtime.totalMemory() - runtime.freeMemory();
//            long end = System.currentTimeMillis();
//
//            System.out.println("execution time : " + (end - start) + " ms");
//            System.out.println("used memory : " + (afterUsedMem - beforeUsedMem) + " bytes");
////            execution time : 409308 ms
////            used memory : 15160528 bytes
//        }
//
//
//        @Test
//        @Transactional
//        @Sql("/sql/disease_course_test.sql")
//        public void 질병_코스_부하_테스트_자바() {
//            Runtime runtime = Runtime.getRuntime();
//            runtime.gc(); // GC 유도
//
//            long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
//            long start = System.currentTimeMillis();
//            for(int i = 0; i < 1000; i++) {
//                for(long u = 1l; u <= 5l; u++) {
//                    List<DiseaseAndCourses> diseaseAndCourses = courseQueryService.queryCoursesForDiseaseOfBrutal(u,
//                            CourseQueryService.RecommendationCriteria.STARS);
//                }
//            }
//            // execution time : 459205
//            // execution time : 137262
//            long afterUsedMem = runtime.totalMemory() - runtime.freeMemory();
//            long end = System.currentTimeMillis();
//
//            System.out.println("execution time : " + (end - start) + " ms");
//            System.out.println("used memory : " + (afterUsedMem - beforeUsedMem) + " bytes");
////            execution time : 139455 ms
////            used memory : 5909088 bytes
//        }
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