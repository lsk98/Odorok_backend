package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVisitedCourse is a Querydsl query type for VisitedCourse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisitedCourse extends EntityPathBase<VisitedCourse> {

    private static final long serialVersionUID = -2104267846L;

    public static final QVisitedCourse visitedCourse = new QVisitedCourse("visitedCourse");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final NumberPath<Double> distance = createNumber("distance", Double.class);

    public final NumberPath<Long> endCoordsId = createNumber("endCoordsId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final BooleanPath isFinished = createBoolean("isFinished");

    public final StringPath review = createString("review");

    public final NumberPath<Integer> stars = createNumber("stars", Integer.class);

    public final NumberPath<Long> startCoordsId = createNumber("startCoordsId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> visitedAt = createDateTime("visitedAt", java.time.LocalDateTime.class);

    public QVisitedCourse(String variable) {
        super(VisitedCourse.class, forVariable(variable));
    }

    public QVisitedCourse(Path<? extends VisitedCourse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisitedCourse(PathMetadata metadata) {
        super(VisitedCourse.class, metadata);
    }

}

