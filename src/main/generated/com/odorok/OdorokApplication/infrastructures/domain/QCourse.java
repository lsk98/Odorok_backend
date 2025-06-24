package com.odorok.OdorokApplication.infrastructures.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = 2074301864L;

    public static final QCourse course = new QCourse("course");

    public final BooleanPath brdDiv = createBoolean("brdDiv");

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final BooleanPath cycle = createBoolean("cycle");

    public final NumberPath<Double> distance = createNumber("distance", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idx = createString("idx");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> reqTime = createNumber("reqTime", Double.class);

    public final NumberPath<Integer> reward = createNumber("reward", Integer.class);

    public final StringPath routeIdx = createString("routeIdx");

    public final NumberPath<Integer> sidoCode = createNumber("sidoCode", Integer.class);

    public final NumberPath<Integer> sigunguCode = createNumber("sigunguCode", Integer.class);

    public final StringPath summary = createString("summary");

    public final NumberPath<Long> themeId = createNumber("themeId", Long.class);

    public final StringPath travelerInfo = createString("travelerInfo");

    public QCourse(String variable) {
        super(Course.class, forVariable(variable));
    }

    public QCourse(Path<? extends Course> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourse(PathMetadata metadata) {
        super(Course.class, metadata);
    }

}

