package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAchievementHistory is a Querydsl query type for AchievementHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAchievementHistory extends EntityPathBase<AchievementHistory> {

    private static final long serialVersionUID = -1314360400L;

    public static final QAchievementHistory achievementHistory = new QAchievementHistory("achievementHistory");

    public final DateTimePath<java.time.LocalDateTime> achievedAt = createDateTime("achievedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> achievementId = createNumber("achievementId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAchievementHistory(String variable) {
        super(AchievementHistory.class, forVariable(variable));
    }

    public QAchievementHistory(Path<? extends AchievementHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAchievementHistory(PathMetadata metadata) {
        super(AchievementHistory.class, metadata);
    }

}

