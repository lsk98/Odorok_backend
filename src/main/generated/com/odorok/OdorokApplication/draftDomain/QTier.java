package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTier is a Querydsl query type for Tier
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTier extends EntityPathBase<Tier> {

    private static final long serialVersionUID = -1783621718L;

    public static final QTier tier = new QTier("tier");

    public final NumberPath<Integer> bound = createNumber("bound", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath title = createString("title");

    public QTier(String variable) {
        super(Tier.class, forVariable(variable));
    }

    public QTier(Path<? extends Tier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTier(PathMetadata metadata) {
        super(Tier.class, metadata);
    }

}

