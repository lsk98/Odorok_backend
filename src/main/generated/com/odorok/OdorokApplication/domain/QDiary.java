package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiary is a Querydsl query type for Diary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiary extends EntityPathBase<Diary> {

    private static final long serialVersionUID = -1948255080L;

    public static final QDiary diary = new QDiary("diary");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> vcourseId = createNumber("vcourseId", Long.class);

    public QDiary(String variable) {
        super(Diary.class, forVariable(variable));
    }

    public QDiary(Path<? extends Diary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiary(PathMetadata metadata) {
        super(Diary.class, metadata);
    }

}

