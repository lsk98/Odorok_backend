package com.odorok.OdorokApplication.community.service;

import com.odorok.OdorokApplication.community.dto.request.ArticleRegistRequest;
import com.odorok.OdorokApplication.community.dto.request.ArticleSearchCondition;
import com.odorok.OdorokApplication.community.dto.response.ArticleSummary;
import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import com.odorok.OdorokApplication.draftDomain.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    ArticleImageService articleImageService;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    ArticleTransactionService articleTransactionService;
    @InjectMocks
    ArticleServiceImpl articleService;

    @Test
    void 게시글_조회_정상작동(){
        //given
        LocalDateTime now = LocalDateTime.now();
        List<ArticleSummary> summaries = new ArrayList<>(List.of(new ArticleSummary(
                1L, // id
                "Sample Title",
                "Sample content for the article.",
                now, // createdAt
                10,
                100,
                5,
                0,
                false,
                123L,
                45L,
                "testuser"
        )));
        ArticleSearchCondition cond = new ArticleSearchCondition();
        cond.setCategory(0);
        cond.setSort("likeCount");
        cond.setPageNum(1);
        //when
        when(articleRepository.findByCondition(cond)).thenReturn(summaries);
        //then
        assertEquals(summaries,articleService.findByCondition(cond));
        verify(articleRepository, times(1)).findByCondition(cond);
    }

    @Test
    void 게시글_작성_성공(){
        //given
        List<String> urls = List.of("xxx.com","yyy.com");
        Article article = Article.builder().userId(1L).build();
        List<MultipartFile> images = List.of();
        //when
        when(articleImageService.insertArticleImages(1L,images)).thenReturn(urls);
        //then
        articleService.insertArticle(new ArticleRegistRequest(),images,1L);
        //트랜잭션 메서드까지 전부 수행되었는지 확인
        verify(articleTransactionService,times(1)).insertArticleTransactional(article, urls, 1L);
    }

    @Test
    void 게시글_작성_실패() {
        //given
        List<String> urls = List.of("xxx.com","yyy.com");
        Article article = Article.builder().userId(1L).build();
        List<MultipartFile> images = List.of();
        //when
        when(articleImageService.insertArticleImages(1L,images)).thenReturn(urls);
        doThrow(new TransactionSystemException("트랜잭션 오류"))
                .when(articleTransactionService)
                .insertArticleTransactional(article, urls, 1L);
        //then
        assertThrows(TransactionSystemException.class, () ->
                articleService.insertArticle(new ArticleRegistRequest(), images, 1L));
        //트랜잭션 도중 예외가 발생했을 때 catch문이 정상적으로 실행되는지 확인
        verify(articleImageService,times(1)).deleteImages(urls);
    }
}