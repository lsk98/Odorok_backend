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
import com.odorok.OdorokApplication.security.dto.CustomUserDetails;
import com.odorok.OdorokApplication.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@Tag(name = "코스 관련 API")
public class CourseApiController {
    private final CourseQueryService courseQueryService;
    private final UserService userService;
    private final ProfileQueryService profileQueryService;
    private final CourseScheduleManageService courseScheduleManageService;
    private final CourseScheduleQueryService courseScheduleQueryService;

    @GetMapping("/region")
    @Operation(summary = "지역 코드로 코스를 검색한다.", description = "시도 코드, 시군구 코드를 기반으로 검색합니다. 정렬 기준은 지정가능합니다. 지정하지 않으면, 생성일이 기준입니다. 기본 페이지 사이즈는 10이며 0번 페이지부터 시작합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 요약 정보들이 전송됨")
    @ApiResponse(responseCode = "400", description = "시도 코드가 유효하지 않으면 400 에러가 출력됨.")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<CourseResponse>> searchByRegionCode(@RequestParam("sidoCode") Integer sidoCode,
                                                                           @RequestParam("sigunguCode") Integer sigunguCode,
                                                                           @AuthenticationPrincipal CustomUserDetails user,
                                                                           @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {
        String email = (user != null ? user.getUsername() : null);
        // 페이징 넣기.
        log.debug("지역 코스 검색 리퀘스트 : {}, {}, {}, {}, {}", sidoCode, sigunguCode, email, pageable.getPageNumber(), pageable.getPageSize());

        if(!courseQueryService.checkSidoCodeValidation(sidoCode)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponseBuilder.fail("유효하지 않은 시도 코드 입니다. " + sidoCode));
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
    @Operation(summary = "전체 코스 목록에서 조회한다.", description = "정렬 기준은 지정가능합니다. 지정하지 않으면, 생성일이 기준입니다. 기본 페이지 사이즈는 10이며 0번 페이지부터 시작합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 요약 정보들이 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<CourseResponse>> getAllCourses(
            @AuthenticationPrincipal CustomUserDetails user,
            @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {
        String email = (user != null) ? user.getUsername() : null;
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
    @Operation(summary = "코스의 상세 정보를 조회한다.", description = "어떤 코스에 대해서 가지고 있는 세부 정보 조회를 합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 세부 정보들이 전송됨")
    @ApiResponse(responseCode = "400", description = "존재하지 않는 코스 ID로 조회하는 경우임.")
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
    @Operation(summary = "평균 별점, 방분수, 리뷰 개수 기준으로 상위 코스를 조회한다.", description = "정렬 기준은 지정가능합니다. 지정하지 않으면, 생성일이 기준입니다. 기본 페이지 사이즈는 10이며 0번 페이지부터 시작합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 요약 정보들이 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
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
    @Operation(summary = "질병코스 상에서 통계적으로 조회한다.", description = "사용자 질병 코드로 동일 질병 가진 사람들이 방문한 평균 별점이 가장 높은 코스를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 추천되는 코스 요약 정보들이 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<DiseaseCourseResponse>> getCoursesForDisease(@AuthenticationPrincipal CustomUserDetails user,
                                                                                    @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Long userId = user.getUserId();
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
    @Operation(summary = "사용자 거주 지역 코스를 조회한다.", description = "사용자가 가입시 입력한 거주지 주소 코드로 코스를 조회합니다. 없을 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 추천되는 코스 요약 정보들이 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<CourseResponse>> getUserRegionCourses(@AuthenticationPrincipal CustomUserDetails user,
                                                                             @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Long userId = user.getUserId();
        Profile profile = profileQueryService.queryProfileByUserId(userId);


        List<CourseSummary> summaries = null;
        if(courseQueryService.checkSidoCodeValidation(profile.getSidoCode())) {
            summaries = courseQueryService.queryCoursesByRegion(profile.getSidoCode(), profile.getSigunguCode(), userId, pageable);
        } else {
            summaries = new ArrayList<>();
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("", new CourseResponse(summaries)));
    }

    // 방문 예정 코스 조회
    @GetMapping("/schedule")
    @Operation(summary = "방문 예정으로 등록했던 기록을 불러옵니다..", description = "사용자가 방문하기로 예정을 등록한 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공시 날짜와 코스 정보들이 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<VisitationScheduleResponse>> getCourseSchedule(@AuthenticationPrincipal CustomUserDetails user) {
        List<VisitationScheduleSummary> summaries = courseScheduleQueryService.queryAllSchedule(user.getUserId());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("", new VisitationScheduleResponse(summaries)));
    }

    // 코스 리뷰 조회


    // 예정 등록
    @PostMapping("/schedule")
    @Operation(summary = "코스를 방문 예정으로 등록한다.", description = "특정 코스를 특정 날짜에 방문하기로 등록합니다.")
    @ApiResponse(responseCode = "201", description = "등록 성공시 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 등록에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<?>> registNewVisitationSchedule(@AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CourseScheduleRequest request) {
        courseScheduleManageService.registSchedule(request, user.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.successCreated("코스 방문 예정 등록에 성공했습니다.", null));
    }

    // 코스 리뷰 조회
    @GetMapping("/reviews")
    @Operation(summary = "코스에 대한 리뷰를 조회합니다.", description = "특정 코스에 대한 리뷰를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공 리뷰 데이터가 전송됨")
    @ApiResponse(responseCode = "500", description = "서버 내부에서 조회에 실패하는 경우임.")
    public ResponseEntity<ResponseRoot<VisitationScheduleResponse>> getCourseReviews(@RequestParam("courseId") Long courseId) {
        return null;
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
//                .body(CommonResponseBuilder.success("", new VisitationScheduleResponse(summaries)));
    }
}
