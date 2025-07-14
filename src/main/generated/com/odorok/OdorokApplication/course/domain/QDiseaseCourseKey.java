package com.odorok.OdorokApplication.course.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiseaseCourseKey is a Querydsl query type for DiseaseCourseKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDiseaseCourseKey extends BeanPath<DiseaseCourseKey> {

    private static final long serialVersionUID = -1732057538L;

    public static final QDiseaseCourseKey diseaseCourseKey = new QDiseaseCourseKey("diseaseCourseKey");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final NumberPath<Long> diseaseId = createNumber("diseaseId", Long.class);

    public QDiseaseCourseKey(String variable) {
        super(DiseaseCourseKey.class, forVariable(variable));
    }

    public QDiseaseCourseKey(Path<? extends DiseaseCourseKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiseaseCourseKey(PathMetadata metadata) {
        super(DiseaseCourseKey.class, metadata);
    }

}

