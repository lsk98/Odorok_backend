package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContentType is a Querydsl query type for ContentType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContentType extends EntityPathBase<ContentType> {

    private static final long serialVersionUID = 1487404171L;

    public static final QContentType contentType = new QContentType("contentType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QContentType(String variable) {
        super(ContentType.class, forVariable(variable));
    }

    public QContentType(Path<? extends ContentType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContentType(PathMetadata metadata) {
        super(ContentType.class, metadata);
    }

}

