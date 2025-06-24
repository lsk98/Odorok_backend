package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScheduledAttraction is a Querydsl query type for ScheduledAttraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScheduledAttraction extends EntityPathBase<ScheduledAttraction> {

    private static final long serialVersionUID = -1866348535L;

    public static final QScheduledAttraction scheduledAttraction = new QScheduledAttraction("scheduledAttraction");

    public final NumberPath<Long> attractionId = createNumber("attractionId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> scourseId = createNumber("scourseId", Long.class);

    public QScheduledAttraction(String variable) {
        super(ScheduledAttraction.class, forVariable(variable));
    }

    public QScheduledAttraction(Path<? extends ScheduledAttraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScheduledAttraction(PathMetadata metadata) {
        super(ScheduledAttraction.class, metadata);
    }

}

