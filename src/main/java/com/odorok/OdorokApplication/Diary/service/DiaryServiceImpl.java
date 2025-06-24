package com.odorok.OdorokApplication.Diary.service;

import com.odorok.OdorokApplication.Diary.dto.DiaryDetail;
import com.odorok.OdorokApplication.Diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService{
    public final DiaryRepository diaryRepository;

    public DiaryDetail findDiaryById(long userId, long diaryId){
        return diaryRepository.findDiaryById(userId, diaryId);
    }


}
