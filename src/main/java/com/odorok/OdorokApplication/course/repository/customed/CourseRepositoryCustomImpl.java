package com.odorok.OdorokApplication.course.repository.customed;

import com.odorok.OdorokApplication.infrastructures.domain.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QCourse course = QCourse.course;

    @Override
    public Set<Integer> findDistinctValidSidoCodes() {
        return new HashSet<>(queryFactory.select(course.sidoCode).distinct().from(course).fetch());
    }
}
