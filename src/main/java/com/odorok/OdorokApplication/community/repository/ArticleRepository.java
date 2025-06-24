package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.draftDomain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleRepositoryCustom {
}
