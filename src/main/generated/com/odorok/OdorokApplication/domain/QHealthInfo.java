package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHealthInfo is a Querydsl query type for HealthInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthInfo extends EntityPathBase<HealthInfo> {

    private static final long serialVersionUID = 1344336245L;

    public static final QHealthInfo healthInfo = new QHealthInfo("healthInfo");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> diseaseMask = createNumber("diseaseMask", Long.class);

    public final NumberPath<Integer> drinkPerWeek = createNumber("drinkPerWeek", Integer.class);

    public final NumberPath<Integer> exercisePerWeek = createNumber("exercisePerWeek", Integer.class);

    public final BooleanPath gender = createBoolean("gender");

    public final NumberPath<Double> height = createNumber("height", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath smoking = createBoolean("smoking");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QHealthInfo(String variable) {
        super(HealthInfo.class, forVariable(variable));
    }

    public QHealthInfo(Path<? extends HealthInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthInfo(PathMetadata metadata) {
        super(HealthInfo.class, metadata);
    }

}

