package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDisease is a Querydsl query type for UserDisease
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDisease extends EntityPathBase<UserDisease> {

    private static final long serialVersionUID = 2026682438L;

    public static final QUserDisease userDisease = new QUserDisease("userDisease");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> diseaseId = createNumber("diseaseId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserDisease(String variable) {
        super(UserDisease.class, forVariable(variable));
    }

    public QUserDisease(Path<? extends UserDisease> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDisease(PathMetadata metadata) {
        super(UserDisease.class, metadata);
    }

}

