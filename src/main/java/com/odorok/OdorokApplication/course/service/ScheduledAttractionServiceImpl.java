package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.repository.ScheduledAttractionRepository;
import com.odorok.OdorokApplication.domain.ScheduledAttraction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledAttractionServiceImpl implements ScheduledAttractionService {
    private final ScheduledAttractionRepository scheduledAttractionRepository;

    @Override
    public List<ScheduledAttraction> queryScheduledAttractions(Long scheduledCourseId) {
        log.debug("다음 방문 예정에 대한 명소 조회 : {}", scheduledCourseId);
        return scheduledAttractionRepository.findByScourseId(scheduledCourseId);
    }

    @Override
    @Transactional
    public void registAttractionSchedules(List<Long> ids, Long scheduledCourseId) {
        log.debug("다음 방문 예정에 대한 명소 등록 : {}", scheduledCourseId);
        List<ScheduledAttraction> targets = new ArrayList<>();
        for(Long id : ids) {
            ScheduledAttraction attraction = new ScheduledAttraction(null, id, scheduledCourseId);
            targets.add(attraction);
        }

        scheduledAttractionRepository.saveAll(targets);
    }

    @Override
    @Transactional
    public void deleteAttractionSchedules(Long scheduledCourseId) {
        scheduledAttractionRepository.deleteByScourseId(scheduledCourseId);
    }
}
