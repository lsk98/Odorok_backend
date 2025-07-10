package com.odorok.OdorokApplication.course.dto.process;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.odorok.OdorokApplication.course.dto.process.QCourseStat is a Querydsl Projection type for CourseStat
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCourseStat extends ConstructorExpression<CourseStat> {

    private static final long serialVersionUID = -1514450703L;

    public QCourseStat(com.querydsl.core.types.Expression<Long> courseId, com.querydsl.core.types.Expression<Double> avgStars, com.querydsl.core.types.Expression<Long> reviewCount, com.querydsl.core.types.Expression<Long> visitationCount) {
        super(CourseStat.class, new Class<?>[]{long.class, double.class, long.class, long.class}, courseId, avgStars, reviewCount, visitationCount);
    }

}

