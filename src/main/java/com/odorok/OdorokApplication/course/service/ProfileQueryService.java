package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.draftDomain.Profile;

public interface ProfileQueryService {
    Profile queryProfileByUserId(Long userId);
}
