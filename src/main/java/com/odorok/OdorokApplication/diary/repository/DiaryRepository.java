package com.odorok.OdorokApplication.diary.repository;

import com.odorok.OdorokApplication.diary.dto.DiaryDetail;

public interface DiaryRepository {
    public DiaryDetail findDiaryById(long userId, long diaryId);
}
