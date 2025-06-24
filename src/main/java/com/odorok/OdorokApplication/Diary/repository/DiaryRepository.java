package com.odorok.OdorokApplication.Diary.repository;

import com.odorok.OdorokApplication.Diary.dto.DiaryDetail;

public interface DiaryRepository {
    public DiaryDetail findDiaryById(long userId, long diaryId);
}
