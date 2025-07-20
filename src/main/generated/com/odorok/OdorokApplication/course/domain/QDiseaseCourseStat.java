package com.odorok.OdorokApplication.course.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiseaseCourseStat is a Querydsl query type for DiseaseCourseStat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiseaseCourseStat extends EntityPathBase<DiseaseCourseStat> {

    private static final long serialVersionUID = 2141043285L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiseaseCourseStat diseaseCourseStat = new QDiseaseCourseStat("diseaseCourseStat");

    public final NumberPath<java.math.BigDecimal> avgStars = createNumber("avgStars", java.math.BigDecimal.class);

    public final QDiseaseCourseKey key;

    public final NumberPath<Long> reviewCount = createNumber("reviewCount", Long.class);

    public final NumberPath<Long> visitationCount = createNumber("visitationCount", Long.class);

    public QDiseaseCourseStat(String variable) {
        this(DiseaseCourseStat.class, forVariable(variable), INITS);
    }

    public QDiseaseCourseStat(Path<? extends DiseaseCourseStat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiseaseCourseStat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiseaseCourseStat(PathMetadata metadata, PathInits inits) {
        this(DiseaseCourseStat.class, metadata, inits);
    }

    public QDiseaseCourseStat(Class<? extends DiseaseCourseStat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QDiseaseCourseKey(forProperty("key")) : null;
    }

}

