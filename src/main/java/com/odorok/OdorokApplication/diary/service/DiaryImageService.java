package com.odorok.OdorokApplication.diary.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiaryImageService {
    public List<String> insertDiaryImage(List<MultipartFile> images, Long userId);
    public void insertDiaryImageUrl(List<String> imgUrls, Long diaryId);
    public void deleteDiaryImages(List<String> imgUrls);
}
