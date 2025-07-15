package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.domain.UserDisease;

import java.util.List;

public interface UserDiseaseQueryService {
    List<UserDisease> queryUserDiseases(Long userId);
    List<UserDisease> queryUsersHavingDisease(Long diseaseId);
}
