package com.odorok.OdorokApplication.community.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleImageService {
    public void insertArticleImages(Long ArticleId, List<MultipartFile> images);
    public void deleteArticleImages(Long id);
    public void deleteArticleImageUrl(Long id);
    public void insertArticleImageUrl(Long id,List<String> urls);
    public List<String> findArticleImageUrlById(Long id);
    public List<String> updateArticleImageUrl(Long id,List<String> afterUrl);
}
