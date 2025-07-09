package com.odorok.OdorokApplication.community.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleImageService {

    public List<String> insertArticleImages(Long userId, List<MultipartFile> images);
    public List<String> deleteArticleImageUrl(Long articleId);
    public void insertArticleImageUrl(Long articleId,List<String> urls);
    public void deleteImages(List<String> urls);
}
