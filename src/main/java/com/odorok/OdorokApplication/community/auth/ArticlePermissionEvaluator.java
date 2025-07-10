package com.odorok.OdorokApplication.community.auth;

import com.odorok.OdorokApplication.commons.auth.AuthenticationFacade;
import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component("articlePermissionEvaluator")
@RequiredArgsConstructor
public class ArticlePermissionEvaluator {
    private final ArticleRepository articleRepository;
    private final AuthenticationFacade authenticationFacade;

    public boolean isOwner(Long articleId){
        Long currentUser = authenticationFacade.getCurrentUserId();
        Long writer;
        try{
            writer = articleRepository.getById(articleId).getUserId();
        }catch(EntityNotFoundException e){
            throw new AccessDeniedException("일치하지 않는 사용자 요청");
        }
        return currentUser.equals(writer);
    }
}
