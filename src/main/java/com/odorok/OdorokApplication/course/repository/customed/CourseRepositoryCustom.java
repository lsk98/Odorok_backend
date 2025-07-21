package com.odorok.OdorokApplication.course.repository.customed;


import java.util.Set;

public interface CourseRepositoryCustom {
    Set<Integer> findDistinctValidSidoCodes();
}
