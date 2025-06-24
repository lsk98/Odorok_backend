package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = -1822585119L;

    public static final QProfile profile = new QProfile("profile");

    public final NumberPath<Integer> achievement1 = createNumber("achievement1", Integer.class);

    public final NumberPath<Integer> achievement2 = createNumber("achievement2", Integer.class);

    public final NumberPath<Integer> achievement3 = createNumber("achievement3", Integer.class);

    public final NumberPath<Integer> activityPoint = createNumber("activityPoint", Integer.class);

    public final NumberPath<Long> diaryId = createNumber("diaryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final NumberPath<Integer> mileage = createNumber("mileage", Integer.class);

    public final BooleanPath msgAgree = createBoolean("msgAgree");

    public final StringPath msgFrequency = createString("msgFrequency");

    public final NumberPath<Integer> sidoCode = createNumber("sidoCode", Integer.class);

    public final NumberPath<Integer> sigunguCode = createNumber("sigunguCode", Integer.class);

    public final NumberPath<Long> tierId = createNumber("tierId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QProfile(String variable) {
        super(Profile.class, forVariable(variable));
    }

    public QProfile(Path<? extends Profile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfile(PathMetadata metadata) {
        super(Profile.class, metadata);
    }

}

