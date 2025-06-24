package com.odorok.OdorokApplication.infrastructures.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSigungu is a Querydsl query type for Sigungu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSigungu extends EntityPathBase<Sigungu> {

    private static final long serialVersionUID = 1009385899L;

    public static final QSigungu sigungu = new QSigungu("sigungu");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sidoCode = createNumber("sidoCode", Integer.class);

    public QSigungu(String variable) {
        super(Sigungu.class, forVariable(variable));
    }

    public QSigungu(Path<? extends Sigungu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSigungu(PathMetadata metadata) {
        super(Sigungu.class, metadata);
    }

}

