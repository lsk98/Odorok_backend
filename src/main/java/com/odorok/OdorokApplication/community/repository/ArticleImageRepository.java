package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.draftDomain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage,Long> {
    void deleteByArticleId(Long articleId);

    @Query("select ai.imgUrl from article_images ai where ai.articleId = :articleId")
    List<String> findImgUrlsByArticleId(@Param("articleId") Long articleId);
}
