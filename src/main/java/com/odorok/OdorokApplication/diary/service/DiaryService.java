package com.odorok.OdorokApplication.diary.service;

import com.odorok.OdorokApplication.diary.dto.DiaryDetail;

public interface DiaryService {
//    public List<DiarySummary> findAllDiaryByMember(long userId);
    public DiaryDetail findDiaryById(long userId, long diaryId);

}
