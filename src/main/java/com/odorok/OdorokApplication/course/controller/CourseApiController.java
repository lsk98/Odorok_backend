package com.odorok.OdorokApplication.course.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.dto.request.CourseScheduleRequest;
import com.odorok.OdorokApplication.course.dto.response.holder.CourseResponse;
import com.odorok.OdorokApplication.course.dto.response.holder.DiseaseCourseResponse;
import com.odorok.OdorokApplication.course.dto.response.holder.TopRatedCourseResponse;
import com.odorok.OdorokApplication.course.dto.response.holder.VisitationScheduleResponse;
import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.dto.response.item.DiseaseAndCourses;
import com.odorok.OdorokApplication.course.dto.response.item.VisitationScheduleSummary;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.course.service.CourseScheduleManageService;
import com.odorok.OdorokApplication.course.service.CourseScheduleQueryService;
import com.odorok.OdorokApplication.course.service.ProfileQueryService;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.draftDomain.Profile;
import com.odorok.OdorokApplication.region.exception.InvalidSidoCodeException;
import com.odorok.OdorokApplication.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
public class CourseApiController {
    private final CourseQueryService courseQueryService;
    private final UserService userService;
    private final ProfileQueryService profileQueryService;
    private final CourseScheduleManageService courseScheduleManageService;
    private final CourseScheduleQueryService courseScheduleQueryService;

    @GetMapping("/region")
    public ResponseEntity<ResponseRoot<CourseResponse>> searchByRegionCode(@RequestParam("sidoCode") Integer sidoCode,
                                                                           @RequestParam("sigunguCode") Integer sigunguCode,
                                                                           @RequestParam(value = "email", required = false) String email,
                                                                           @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {
        // 페이징 넣기.
        log.debug("지역 코스 검색 리퀘스트 : {}, {}, {}, {}, {}", sidoCode, sigunguCode, email, pageable.getPageNumber(), pageable.getPageSize());

        if(!courseQueryService.checkSidoCodeValidation(sidoCode)) {
            throw new InvalidSidoCodeException("유효하지 않은 시도 코드 입니다. (sidoCode="+sidoCode+")");
        }

        Long userId = null;
        if (email != null) userId = userService.queryByEmail(email).getId();
        CourseResponse response = new CourseResponse();
        try {
            response.setItems(courseQueryService.queryCoursesByRegion(sidoCode, sigunguCode, userId, pageable));
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonResponseBuilder.fail("지역 코스 검색에 실패했습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponseBuilder.success("", response));
    }

    // 전체 코스 리스트
    @GetMapping("")
    public ResponseEntity<ResponseRoot<CourseResponse>> getAllCourses(
            @RequestParam(value = "email", required = false) String email,
            @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {
        log.debug("전체 코스 검색 리퀘스트 : {}, {}, {}", email, pageable.getPageNumber(), pageable.getPageSize());
        Long userId = null;
        if (email != null) userId = userService.queryByEmail(email).getId();
        try {
            List<CourseSummary> result = courseQueryService.queryAllCourses(userId, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(CommonResponseBuilder.success("", new CourseResponse(result)));
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonResponseBuilder.fail("전체 코스 조회에 실패했습니다."));
        }
    }

    // 코스 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<ResponseRoot<CourseDetail>> getCourseDetail(
            @RequestParam("courseId") Long courseId) {
        log.debug("코스 상세 조회 요청 : courseId={}", courseId);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponseBuilder.success("", courseQueryService.queryCourseDetail(courseId)));
        } catch (IllegalArgumentException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseBuilder.fail("존재하지 않는 코스 아이디(" + courseId + ") 입니다."));
        }
    }

    // Top 코스 리스트
    @GetMapping("/top")
    public ResponseEntity<ResponseRoot<TopRatedCourseResponse>> getTopStarsCourses() {
        log.debug("상위 코스 조회 요청");
        // criteria => "stars", "visit", "reviews"
        TopRatedCourseResponse res = new TopRatedCourseResponse();
        res.setTopStars(courseQueryService.queryTopRatedCourses(CourseQueryService.RecommendationCriteria.STARS));
        res.setTopVisited(courseQueryService.queryTopRatedCourses(CourseQueryService.RecommendationCriteria.TOTAL_VISITATION));
        res.setTopReviewCount(courseQueryService.queryTopRatedCourses(CourseQueryService.RecommendationCriteria.REVIEWS));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("", res));
    }

    // 사용자 질병 코스 리스트
    @GetMapping("/disease")
    public ResponseEntity<ResponseRoot<DiseaseCourseResponse>> getCoursesForDisease(@RequestParam("email") String email,
                                                                                    @PageableDefault(size = 10, page = 0) Pageable pageable) {
        User user = userService.queryByEmail(email);
        Long userId = user.getId();
        log.debug("disease request에 대한 유저 아이디 = " + userId);
        List<DiseaseAndCourses> diseaseAndCourses = courseQueryService.queryCoursesForDiseasesOf(userId, CourseQueryService.RecommendationCriteria.STARS, pageable);
        log.debug("disease request응답 = " + diseaseAndCourses.toString());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(
                CommonResponseBuilder.success("", new DiseaseCourseResponse(diseaseAndCourses))
        );
    }

    // insert into health_infos values(null, 1, 1, 175, 70, 25, 1, 13, 2, 1);g
    // 사용자 지역 코스 리스트
    @GetMapping("/user-region")
    public ResponseEntity<ResponseRoot<CourseResponse>> getUserRegionCourses(@RequestParam("email") String email,
                                                                             @PageableDefault(size = 10, page = 0) Pageable pageable) {
        User user = userService.queryByEmail(email);
        Profile profile = profileQueryService.queryProfileByUserId(user.getId());

        List<CourseSummary> summaries = null;
        if(courseQueryService.checkSidoCodeValidation(profile.getSidoCode())) {
            summaries = courseQueryService.queryCoursesByRegion(profile.getSidoCode(), profile.getSigunguCode(), user.getId(), pageable);
        } else {
            summaries = new ArrayList<>();
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("", new CourseResponse(summaries)));
    }

    // 방문 예정 코스 조회
    @GetMapping("/schedule")
    public ResponseEntity<ResponseRoot<VisitationScheduleResponse>> getCourseSchedule(@RequestParam("email") String email) {
        User user = userService.queryByEmail(email);
        List<VisitationScheduleSummary> summaries = courseScheduleQueryService.queryAllSchedule(user.getId());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("", new VisitationScheduleResponse(summaries)));
    }

    // 코스 리뷰 조회


    // 예정 등록
    @PostMapping("/schedule")
    public ResponseEntity<ResponseRoot<?>> registNewVisitationSchedule(@RequestBody CourseScheduleRequest request) {
        User user = userService.queryByEmail(request.getEmail());
        courseScheduleManageService.registSchedule(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.successCreated("코스 방문 예정 등록에 성공했습니다.", null));
    }
}
