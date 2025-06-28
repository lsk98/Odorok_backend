package com.odorok.OdorokApplication.community.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleImageService {

    public List<String> insertArticleImages(Long userId, List<MultipartFile> images);
    public void deleteArticleImages(Long articleId);
    public void deleteArticleImageUrl(Long articleId);
    public void insertArticleImageUrl(Long articleId,List<String> urls);
    public List<String> findArticleImageUrlById(Long id);
    public List<String> updateArticleImageUrl(Long id,List<String> afterUrls);
    public void deleteImages(List<String> urls);
}
