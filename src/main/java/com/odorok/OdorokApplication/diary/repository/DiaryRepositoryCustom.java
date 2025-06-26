package com.odorok.OdorokApplication.diary.repository;

import com.odorok.OdorokApplication.diary.dto.DiaryDetail;
import com.odorok.OdorokApplication.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepositoryCustom {
    public DiaryDetail findDiaryById(long userId, long diaryId);
}