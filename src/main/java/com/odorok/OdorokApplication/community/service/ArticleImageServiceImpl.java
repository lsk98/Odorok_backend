package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.repository.ArticleImageRepository;
import com.odorok.OdorokApplication.draftDomain.ArticleImage;
import com.odorok.OdorokApplication.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService{
    private final S3Service s3Service;

    private final ArticleImageRepository articleImageRepository;

    @Override
    public List<String> insertArticleImages(Long userId, List<MultipartFile> images) {
        return s3Service.uploadMany("community", String.valueOf(userId),images);
    }

    @Override
    public void deleteArticleImages(Long articleId) {

    }

    @Override
    public void deleteArticleImageUrl(Long articleId) {

    }

    @Override
    public void insertArticleImageUrl(Long userId, List<String> urls) {
        List<ArticleImage> images = new ArrayList();
        for(String url : urls){
            images.add(new ArticleImage(userId,url));
        }
        articleImageRepository.saveAll(images);
    }

    @Override
    public List<String> findArticleImageUrlById(Long id) {
        return null;
    }

    @Override
    public List<String> updateArticleImageUrl(Long id, List<String> afterUrl) {
        return null;
    }

    @Override
    public void deleteImages(List<String> urls) {
        s3Service.deleteMany(urls);
    }
}
