package com.odorok.OdorokApplication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceHistory is a Querydsl query type for AttendanceHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceHistory extends EntityPathBase<AttendanceHistory> {

    private static final long serialVersionUID = 1352544960L;

    public static final QAttendanceHistory attendanceHistory = new QAttendanceHistory("attendanceHistory");

    public final DateTimePath<java.time.LocalDateTime> attendedAt = createDateTime("attendedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAttendanceHistory(String variable) {
        super(AttendanceHistory.class, forVariable(variable));
    }

    public QAttendanceHistory(Path<? extends AttendanceHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceHistory(PathMetadata metadata) {
        super(AttendanceHistory.class, metadata);
    }

}

