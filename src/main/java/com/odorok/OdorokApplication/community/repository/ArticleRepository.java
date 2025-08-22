package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.draftDomain.Article;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleRepositoryCustom {
    default Article getById(Long articleId){
        return findById(articleId).orElseThrow(()-> new EntityNotFoundException("게시물이 존재하지 않습니다"));
    }
}
