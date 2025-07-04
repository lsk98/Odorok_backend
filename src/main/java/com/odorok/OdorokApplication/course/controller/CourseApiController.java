package com.odorok.OdorokApplication.course.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.dto.response.holder.CourseResponse;
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
            log.debug("지역 코스 검색 리퀘스트 {}, {}, {}, {}, {}", sidoCode, sigunguCode, email, pageable.getPageNumber(), pageable.getPageSize());
            Long userId = null;
            if(email != null) userId = userService.selectByEmail(email).getId();
            CourseResponse response = new CourseResponse();
            try {
                response.setItems(courseQueryService.findCoursesByRegion(sidoCode, sigunguCode, userId, pageable));
            } catch(RuntimeException e) {
                log.debug(e.getMessage());
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(CommonResponseBuilder.success("", response));
    }
}
