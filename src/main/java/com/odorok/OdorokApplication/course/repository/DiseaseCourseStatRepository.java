package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.course.domain.DiseaseCourseKey;
import com.odorok.OdorokApplication.course.domain.DiseaseCourseStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseCourseStatRepository extends JpaRepository<DiseaseCourseStat, DiseaseCourseKey> {
    List<DiseaseCourseStat> findByKeyDiseaseId(Long diseaseId);
}