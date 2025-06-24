package com.odorok.OdorokApplication.infrastructures.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPathCoord is a Querydsl query type for PathCoord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPathCoord extends EntityPathBase<PathCoord> {

    private static final long serialVersionUID = 1963014435L;

    public static final QPathCoord pathCoord = new QPathCoord("pathCoord");

    public final NumberPath<Long> courseId = createNumber("courseId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final NumberPath<Integer> ordering = createNumber("ordering", Integer.class);

    public QPathCoord(String variable) {
        super(PathCoord.class, forVariable(variable));
    }

    public QPathCoord(Path<? extends PathCoord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPathCoord(PathMetadata metadata) {
        super(PathCoord.class, metadata);
    }

}

