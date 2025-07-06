package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.commons.querydsl.config.QueryDslConfig;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(QueryDslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void 지역_코드로_코스_조회에_성공한다() {
        int sidoCode = 38, sigunguCode = 2;
        Assertions.assertThat(courseRepository.findBySidoCodeAndSigunguCode(sidoCode, sigunguCode, Pageable.ofSize(10)).getContent().size()).isLessThanOrEqualTo(10);
    }

    @Test
    public void 페이지_범위_바깥_조회에_성공한다() {
        Pageable pageable = PageRequest.of(10000, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> page = courseRepository.findAll(pageable);
        System.out.println(page.getTotalPages());
        assertThat(page.getContent().size()).isZero();
    }

    @Test
    public void 전체_코스_조회에_성공한다() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> page = courseRepository.findAll(pageable);
        System.out.println(page.getTotalPages());
        assertThat(page.getContent().size()).isNotZero();
    }
}