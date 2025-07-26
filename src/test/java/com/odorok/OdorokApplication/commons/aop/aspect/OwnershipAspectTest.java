package com.odorok.OdorokApplication.commons.aop.aspect;

import com.odorok.OdorokApplication.community.dto.request.ArticleUpdateRequest;
import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import com.odorok.OdorokApplication.community.service.ArticleService;
import com.odorok.OdorokApplication.course.repository.UserRepository;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.draftDomain.Article;
import com.odorok.OdorokApplication.security.domain.UserStuff;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnershipAspectTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    private User owner;
    private User other;
    private Long articleId;

    @BeforeEach
    void setup(){
        owner = userRepository.save(User.builder().name("ji").nickname("jiji")
                .email("com").password("pass").role("USER").build());
        other = userRepository.save(User.builder().name("fake").nickname("fake")
                .email("fake.com").password("fake").role("USER").build());

        Article article = articleRepository.save(
                new Article("owner의 게시글", 5, 100, 3, 0, false, owner.getId()));
        article.setContent("owner");
        articleId = article.getId();
    }
    private void mockSecurityContext(User user) {
        UserStuff secuUserStuff =
                new UserStuff(user.getId(),user.getName(),user.getNickname(),user.getEmail()
                ,user.getPassword(),"refresh");
        CustomUserDetails userDetails = new CustomUserDetails(secuUserStuff);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void 올바른_권한으로_요청() {
        mockSecurityContext(owner);
        ArticleUpdateRequest req = new ArticleUpdateRequest();
        req.setTitle("수정함");
        req.setContent("수정된내용");

        assertDoesNotThrow(() ->
                articleService.updateArticle(req, List.of(), articleId, owner.getId()));
    }

    @Test
    void 게시글_소유자가_아니면_예외() {
        mockSecurityContext(other);

        ArticleUpdateRequest req = new ArticleUpdateRequest();
        req.setTitle("잘못된 요청으로 수정함");
        req.setContent("잘못 수정된내용");

        assertThrows(AccessDeniedException.class, () ->
                articleService.updateArticle(req, List.of(), articleId, other.getId()));

    }

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

}