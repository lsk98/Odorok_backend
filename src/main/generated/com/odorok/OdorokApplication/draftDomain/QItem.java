package com.odorok.OdorokApplication.draftDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1783938853L;

    public static final QItem item = new QItem("item");

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final BooleanPath onSale = createBoolean("onSale");

    public final StringPath overview = createString("overview");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> reqTier = createNumber("reqTier", Long.class);

    public final StringPath title = createString("title");

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

