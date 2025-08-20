package com.odorok.OdorokApplication.mypage.service;

import com.odorok.OdorokApplication.commons.querydsl.config.QueryDslConfig;
import com.odorok.OdorokApplication.community.repository.ArticleRepository;
import com.odorok.OdorokApplication.community.repository.ProfileRepository;
import com.odorok.OdorokApplication.course.repository.UserRepository;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.draftDomain.Article;
import com.odorok.OdorokApplication.draftDomain.Profile;
import com.odorok.OdorokApplication.mypage.dto.response.UserInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyPageServiceTest {
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    MyPageServiceImpl myPageService;
    @Test
    void 유저_프로필_조회_로직_성공(){
        //given
        UserInfoResponse userInfoResponse = new UserInfoResponse("ss","ss",1,"ss",10);
        //when
        when(profileRepository.findProfileByUserId(1L)).thenReturn(userInfoResponse);

        UserInfoResponse response = myPageService.findUserInfo(1L);
        assertEquals(response.getUserTier(),userInfoResponse.getUserTier());
    }
}