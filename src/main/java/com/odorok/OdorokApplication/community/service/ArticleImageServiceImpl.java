package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService{
    private final S3Service s3Service;

    @Override
    public void insertArticleImages(Long ArticleId, List<MultipartFile> images) {

    }

    @Override
    public void deleteArticleImages(Long id) {

    }

    @Override
    public void deleteArticleImageUrl(Long id) {

    }

    @Override
    public void insertArticleImageUrl(Long id, List<String> urls) {

    }

    @Override
    public List<String> findArticleImageUrlById(Long id) {
        return null;
    }

    @Override
    public List<String> updateArticleImageUrl(Long id, List<String> afterUrl) {
        return null;
    }
}
