package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointHistory is a Querydsl query type for PointHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointHistory extends EntityPathBase<PointHistory> {

    private static final long serialVersionUID = 1058767471L;

    public static final QPointHistory pointHistory = new QPointHistory("pointHistory");

    public final DateTimePath<java.time.LocalDateTime> aquiredAt = createDateTime("aquiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final NumberPath<Long> attendanceId = createNumber("attendanceId", Long.class);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> vcourseId = createNumber("vcourseId", Long.class);

    public QPointHistory(String variable) {
        super(PointHistory.class, forVariable(variable));
    }

    public QPointHistory(Path<? extends PointHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointHistory(PathMetadata metadata) {
        super(PointHistory.class, metadata);
    }

}

