package com.odorok.OdorokApplication.diary.repository;

import com.odorok.OdorokApplication.diary.dto.response.DiaryDetail;
import com.odorok.OdorokApplication.diary.dto.response.DiarySummary;

import java.util.List;

public interface DiaryRepositoryCustom {
    public DiaryDetail findDiaryById(long userId, long diaryId);
    public List<DiarySummary> findDiaryByUserId(long userId);
}