package com.odorok.OdorokApplication.course.dto.response.holder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class PagedCourseResponse extends CourseResponse{
    private Integer curPage;
    private Integer startPage;
    private Integer endPage;
}
