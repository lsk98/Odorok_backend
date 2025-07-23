package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.community.repository.ProfileRepository;
import com.odorok.OdorokApplication.draftDomain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    @Override
    public Profile queryProfileByUserId(final Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않는 아이디 입니다. (ID : " + userId +")"));
    }
}
