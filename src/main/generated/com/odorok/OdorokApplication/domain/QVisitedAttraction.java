package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVisitedAttraction is a Querydsl query type for VisitedAttraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisitedAttraction extends EntityPathBase<VisitedAttraction> {

    private static final long serialVersionUID = 1701315462L;

    public static final QVisitedAttraction visitedAttraction = new QVisitedAttraction("visitedAttraction");

    public final NumberPath<Long> attractionId = createNumber("attractionId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> vcourseId = createNumber("vcourseId", Long.class);

    public QVisitedAttraction(String variable) {
        super(VisitedAttraction.class, forVariable(variable));
    }

    public QVisitedAttraction(Path<? extends VisitedAttraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisitedAttraction(PathMetadata metadata) {
        super(VisitedAttraction.class, metadata);
    }

}

