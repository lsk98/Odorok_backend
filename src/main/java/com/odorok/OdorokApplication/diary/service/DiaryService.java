package com.odorok.OdorokApplication.diary.service;

import com.odorok.OdorokApplication.diary.dto.response.DiaryDetail;
import com.odorok.OdorokApplication.diary.dto.response.DiaryPermissionCheckResponse;

public interface DiaryService {
//    public List<DiarySummary> findAllDiaryByMember(long userId);
    public DiaryDetail findDiaryById(long userId, long diaryId);
    public DiaryPermissionCheckResponse findDiaryPermission(long userId);
}
