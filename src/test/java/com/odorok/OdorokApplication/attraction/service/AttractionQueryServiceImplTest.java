package com.odorok.OdorokApplication.attraction.service;

import com.odorok.OdorokApplication.attraction.dto.response.holder.AttractionResponse;
import com.odorok.OdorokApplication.attraction.dto.response.item.AttractionDetail;
import com.odorok.OdorokApplication.attraction.dto.response.item.AttractionSummary;
import com.odorok.OdorokApplication.attraction.dto.response.item.ContentTypeSummary;
import com.odorok.OdorokApplication.attraction.repository.AttractionRepository;
import com.odorok.OdorokApplication.attraction.repository.ContentTypeRepository;
import com.odorok.OdorokApplication.draftDomain.Attraction;
import com.odorok.OdorokApplication.draftDomain.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AttractionQueryServiceImplTest {
    @Mock
    private ContentTypeRepository contentTypeRepository;

    @Mock
    private AttractionRepository attractionRepository;

    @InjectMocks
    private AttractionQueryServiceImpl attractionQueryService;

    private Map<String, Integer> contentTypeIdMap = Map.of(
            "관광지", 12, "문화시설", 14,
            "축제공연행사", 15, "여행코스", 25,
            "레포츠", 28, "숙박", 32,
            "쇼핑", 38);

    private final Integer SEOUL_SIDO_CODE = 1;
    private final Integer SEOUL_GANNAMGU_SIGUNGU_CODE = 1;

    private final String DUMMY_OVERVIEW = "test 개요";

    @Test
    public void 컨텐츠_타입_아이디_조회에_성공한다() {
        Mockito.when(contentTypeRepository.findAll()).thenReturn(
                List.of(
                        new ContentType(1, "문화시설"),
                        new ContentType(2, "운동시설"),
                        new ContentType(1, "음식점")));

        List<ContentTypeSummary> result = attractionQueryService.queryAllContentTypes();

        assertThat(result.size()).isNotZero();
        Mockito.verify(contentTypeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void 행정구역_정보_컨텐츠종류코드로_명소_검색에_성공한다() {
        // given
        Mockito.when(attractionRepository.findBySidoCodeAndSigunguCodeAndContentTypeId(
                SEOUL_SIDO_CODE, SEOUL_GANNAMGU_SIGUNGU_CODE, contentTypeIdMap.get("쇼핑")
        )).thenReturn(List.of(
           new Attraction(), new Attraction(), new Attraction()
        ));

        // when
        List<AttractionSummary> result = attractionQueryService.queryRegionalAttractions(
                SEOUL_SIDO_CODE, SEOUL_GANNAMGU_SIGUNGU_CODE, contentTypeIdMap.get("쇼핑"));

        // then
        assertThat(result.size()).isEqualTo(3);
        Mockito.verify(attractionRepository, Mockito.times(1)).findBySidoCodeAndSigunguCodeAndContentTypeId(
                SEOUL_SIDO_CODE, SEOUL_GANNAMGU_SIGUNGU_CODE, contentTypeIdMap.get("쇼핑"));
    }

    @Test
    public void 명소_상세_조회에_성공한다() {
        Mockito.when(attractionRepository.findById(1L)).thenReturn(Optional.of(Attraction.builder().overview(DUMMY_OVERVIEW).build()));

        AttractionDetail result = attractionQueryService.queryAttractionDetail(1L);

        assertThat(result).isNotNull();
        assertThat(result.getOverview()).isEqualTo(DUMMY_OVERVIEW);
        Mockito.verify(attractionRepository, Mockito.times(1)).findById(1L);
    }
}