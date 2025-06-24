package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttraction is a Querydsl query type for Attraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttraction extends EntityPathBase<Attraction> {

    private static final long serialVersionUID = 1623502639L;

    public static final QAttraction attraction = new QAttraction("attraction");

    public final StringPath addr1 = createString("addr1");

    public final StringPath addr2 = createString("addr2");

    public final NumberPath<Integer> contentId = createNumber("contentId", Integer.class);

    public final NumberPath<Integer> contentTypeId = createNumber("contentTypeId", Integer.class);

    public final StringPath firstImage1 = createString("firstImage1");

    public final StringPath firstImage2 = createString("firstImage2");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final NumberPath<Integer> mapLevel = createNumber("mapLevel", Integer.class);

    public final StringPath overview = createString("overview");

    public final NumberPath<Integer> sidoCode = createNumber("sidoCode", Integer.class);

    public final NumberPath<Integer> sigunguCode = createNumber("sigunguCode", Integer.class);

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public QAttraction(String variable) {
        super(Attraction.class, forVariable(variable));
    }

    public QAttraction(Path<? extends Attraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttraction(PathMetadata metadata) {
        super(Attraction.class, metadata);
    }

}

