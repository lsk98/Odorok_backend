package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfileDeco is a Querydsl query type for ProfileDeco
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfileDeco extends EntityPathBase<ProfileDeco> {

    private static final long serialVersionUID = 2053744430L;

    public static final QProfileDeco profileDeco = new QProfileDeco("profileDeco");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final NumberPath<Long> profileId = createNumber("profileId", Long.class);

    public QProfileDeco(String variable) {
        super(ProfileDeco.class, forVariable(variable));
    }

    public QProfileDeco(Path<? extends ProfileDeco> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfileDeco(PathMetadata metadata) {
        super(ProfileDeco.class, metadata);
    }

}

