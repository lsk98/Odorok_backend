package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPurchaseHistory is a Querydsl query type for PurchaseHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseHistory extends EntityPathBase<PurchaseHistory> {

    private static final long serialVersionUID = 606294411L;

    public static final QPurchaseHistory purchaseHistory = new QPurchaseHistory("purchaseHistory");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> boughtAt = createDateTime("boughtAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPurchaseHistory(String variable) {
        super(PurchaseHistory.class, forVariable(variable));
    }

    public QPurchaseHistory(Path<? extends PurchaseHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchaseHistory(PathMetadata metadata) {
        super(PurchaseHistory.class, metadata);
    }

}

