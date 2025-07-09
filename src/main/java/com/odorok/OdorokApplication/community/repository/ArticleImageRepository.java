package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.draftDomain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage,Long> {
    void deleteByArticleId(Long articleId);
    @Query("select imgUrl from ArticleImage where articleId = :articleId")
    List<String> findImgUrlsByArticleId(@Param("articleId") Long articleId);
}
