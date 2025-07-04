package com.odorok.OdorokApplication.region.service;

import com.odorok.OdorokApplication.region.repository.SidoRepository;
import com.odorok.OdorokApplication.region.repository.SigunguRepository;
import com.odorok.OdorokApplication.infrastructures.domain.Sido;
import com.odorok.OdorokApplication.infrastructures.domain.Sigungu;
import com.odorok.OdorokApplication.region.dto.response.item.SidoSummary;
import com.odorok.OdorokApplication.region.dto.response.item.SigunguSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class RegionQueryServiceImplTest {
    @Mock
    private SidoRepository sidoRepository;

    @Mock
    private SigunguRepository sigunguRepository;

    @InjectMocks
    private RegionQueryServiceImpl regionQueryService;

    private final int SEOUL_SIDO_CODE = 1;

    @Test
    public void 모든_시도_조회에_성공한다() {
        Mockito.when(sidoRepository.findAll()).thenReturn(List.of(new Sido(), new Sido()));

        List<SidoSummary> result = regionQueryService.queryAllSidos();
        assertThat(result.size()).isNotZero();
        Mockito.verify(sidoRepository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void 서울특별시_행정동_조회에_성공한다() {
        Mockito.when(sigunguRepository.findBySidoCode(SEOUL_SIDO_CODE)).thenReturn(List.of(new Sigungu(), new Sigungu()));

        List<SigunguSummary> result = regionQueryService.queryAllSigunguOf(SEOUL_SIDO_CODE);
        assertThat(result.size()).isNotZero();
        Mockito.verify(sigunguRepository, Mockito.times(1)).findBySidoCode(SEOUL_SIDO_CODE);
    }
}