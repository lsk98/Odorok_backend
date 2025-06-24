package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScheduledCourse is a Querydsl query type for ScheduledCourse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScheduledCourse extends EntityPathBase<ScheduledCourse> {

    private static final long serialVersionUID = 1026681277L;

    public static final QScheduledCourse scheduledCourse = new QScheduledCourse("scheduledCourse");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QScheduledCourse(String variable) {
        super(ScheduledCourse.class, forVariable(variable));
    }

    public QScheduledCourse(Path<? extends ScheduledCourse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScheduledCourse(PathMetadata metadata) {
        super(ScheduledCourse.class, metadata);
    }

}

