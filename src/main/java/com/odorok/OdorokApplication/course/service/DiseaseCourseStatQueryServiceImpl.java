//package com.odorok.OdorokApplication.course.service;
//
//import com.odorok.OdorokApplication.course.domain.DiseaseCourseStat;
//import com.odorok.OdorokApplication.course.repository.DiseaseCourseStatRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DiseaseCourseStatQueryServiceImpl implements DiseaseCourseStatQueryService{
//    private final DiseaseCourseStatRepository diseaseCourseStatRepository;
//
//    @Override
//    public List<DiseaseCourseStat> queryDiseaseCourseStatFor(Long diseaseId) {
//        return diseaseCourseStatRepository.findByKeyDiseaseId(diseaseId);
//    }
//}
