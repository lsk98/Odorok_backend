package com.odorok.OdorokApplication.diary.repository;

import com.odorok.OdorokApplication.config.QueryDslConfig;
import com.odorok.OdorokApplication.diary.dto.DiaryDetail;
import com.odorok.OdorokApplication.domain.Diary;
import com.odorok.OdorokApplication.domain.DiaryImage;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.domain.VisitedCourse;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfig.class)
public class DiaryRepositoryTest {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void setUp() {
        em.persist(mockUser);
        em.persist(mockVisitedCourse);
        em.persist(mockCourse);
        em.persist(mockDiary);
        em.persist(mockDiaryImage1);
        em.persist(mockDiaryImage2);
    }

    @Test
    void 일지_상세정보_조회_성공() {
        long userId = 1L;
        long diaryId = 1L;
        DiaryDetail result = diaryRepository.findDiaryById(userId, diaryId);
        assertNotNull(result);
        assertEquals(mockDiary.getTitle(), result.getTitle());
        assertTrue(result.getContent().contains("산책을 다녀왔다"));
        assertEquals(mockCourse.getName(), result.getCourseName());
        assertEquals(mockVisitedCourse.getVisitedAt(), result.getVisitedAt());

        List<String> expectedImgs = List.of(
                mockDiaryImage1.getImgUrl(),
                mockDiaryImage2.getImgUrl()
        );
        List<String> actualImgs = result.getImgs();

        assertTrue(actualImgs.containsAll(expectedImgs) && expectedImgs.containsAll(actualImgs));
    }

    User mockUser = User.builder()
            .id(1L)
            .name("김철수")
            .nickname("철수")
            .email("abc123@gmail.com")
            .password("Qwer1234")
            .role("USER")
            .build();
    Diary mockDiary = Diary.builder()
            .id(1L)
            .userId(1L)
            .title("2025년 6월 25일의 일기")
            .content("오늘은 산책을 다녀왔다. 중간에 살짝 뛰었는데 죽는 줄 알았다. 운동 좀 더 해야할 것 같다. ")
            .createdAt(LocalDateTime.of(2025, 6, 25, 14, 30, 0))
            .vcourseId(1L)
            .build();
    DiaryImage mockDiaryImage1 = DiaryImage.builder()
            .diaryId(1L)
            .id(1L)
            .imgUrl("mockDiaryImg1.com")
            .build();
    DiaryImage mockDiaryImage2 = DiaryImage.builder()
            .diaryId(1L)
            .id(2L)
            .imgUrl("mockDiaryImg2.com")
            .build();
    VisitedCourse mockVisitedCourse = VisitedCourse.builder()
            .id(1L)
            .courseId(1L)
            .visitedAt(LocalDateTime.of(2025, 6, 25, 14, 30))  // 예시 날짜/시간
            .userId(1L)
            .startCoordsId(200L)
            .endCoordsId(201L)
            .imgUrl("https://via.placeholder.com/300x200?text=VisitedCourse")
            .stars(4)
            .review("정말 좋은 코스였어요!")
            .isFinished(true)
            .build();

    Course mockCourse = Course.builder()
            .id(1L)
            .themeId(1L)
            .idx("CRS001")
            .routeIdx("R001")
            .name("한강 자전거 코스")
            .distance(15.2)
            .reqTime(2.5)
            .level(2)
            .cycle(true)
            .summary("한강을 따라 달리는 시원한 자전거 길입니다.")
            .contents("서울의 중심을 가로지르며 자연과 도시를 동시에 느낄 수 있는 명품 코스입니다.")
            .travelerInfo("자전거 이용 가능, 가족 여행 적합")
            .sidoCode(11)
            .sigunguCode(680)
            .brdDiv(true)
            .createdAt(LocalDateTime.of(2025, 6, 1, 10, 0))
            .modifiedAt(LocalDateTime.of(2025, 6, 20, 12, 0))
            .reward(600) // level 2 * 300
            .build();

}
