package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.repository.UserDiseaseRepository;
import com.odorok.OdorokApplication.domain.UserDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDiseaseQueryServiceImpl implements UserDiseaseQueryService{
    private final UserDiseaseRepository userDiseaseRepository;

    @Override
    public List<UserDisease> queryUserDiseases(Long userId) {
        return userDiseaseRepository.findByUserId(userId);
    }

    @Override
    public List<UserDisease> queryUsersHavingDisease(Long diseaseId) {
        return userDiseaseRepository.findByDiseaseId(diseaseId);
    }
}
