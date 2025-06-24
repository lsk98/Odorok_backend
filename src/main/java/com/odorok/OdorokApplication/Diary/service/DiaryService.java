package com.odorok.OdorokApplication.Diary.service;

import com.odorok.OdorokApplication.Diary.dto.DiaryDetail;

public interface DiaryService {
//    public List<DiarySummary> findAllDiaryByMember(long userId);
    public DiaryDetail findDiaryById(long userId, long diaryId);

}
