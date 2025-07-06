package com.odorok.OdorokApplication.course.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.dto.response.holder.CourseResponse;
import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
public class CourseApiController {
    private final CourseQueryService courseQueryService;
    private final UserService userService;

    @GetMapping("/region")
    public ResponseEntity<ResponseRoot<CourseResponse>> searchByRegionCode(@RequestParam("sidoCode") Integer sidoCode,
                                                                           @RequestParam("sigunguCode") Integer sigunguCode,
                                                                           @RequestParam(value = "email", required = false) String email,
                                                                           @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {
            // 페이징 넣기.
            log.debug("지역 코스 검색 리퀘스트 : {}, {}, {}, {}, {}", sidoCode, sigunguCode, email, pageable.getPageNumber(), pageable.getPageSize());
            Long userId = null;
            if(email != null) userId = userService.selectByEmail(email).getId();
            CourseResponse response = new CourseResponse();
            try {
                response.setItems(courseQueryService.queryCoursesByRegion(sidoCode, sigunguCode, userId, pageable));
            } catch(RuntimeException e) {
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
        log.debug("지역 코스 검색 리퀘스트 : {}, {}, {}", email, pageable.getPageNumber(), pageable.getPageSize());
        Long userId = null;
        if(email != null) userId = userService.selectByEmail(email).getId();
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

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponseBuilder.success("", null));
    }

    // 별점 Top 코스 리스트
    
    // 방문수 Top 코스 리스트
    
    // 사용자 질병 코스 리스트
    
    // 사용자 지역 코스 리스트
    
    // 방문 예정 코스 조회
    
    // 코스 리뷰 조회

    
    // 예정 등록
    
}
