package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.Coord;
import com.odorok.OdorokApplication.course.repository.PathCoordRepository;
import com.odorok.OdorokApplication.infrastructures.domain.PathCoord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class PathCoordQueryServiceImplTest {
    @Mock
    PathCoordRepository pathCoordRepository;

    @InjectMocks
    PathCoordQueryServiceImpl pathCoordQueryService;

    private final static Long COURSE_ID = 1L;

    @Test
    public void 코스_좌표_조회에_성공한다() {
        Mockito.when(pathCoordRepository.findByCourseId(COURSE_ID)).thenReturn(
                List.of(new PathCoord(), new PathCoord(), new PathCoord())
        );

        List<Coord> coords = pathCoordQueryService.queryCoursePathCoords(COURSE_ID);

        assertThat(coords).isNotNull();
        assertThat(coords.size()).isEqualTo(3);
        Mockito.verify(pathCoordRepository, Mockito.times(1)).findByCourseId(COURSE_ID);
    }
}