package com.odorok.OdorokApplication.attraction.service;

import com.odorok.OdorokApplication.attraction.dto.response.item.AttractionDetail;
import com.odorok.OdorokApplication.attraction.dto.response.item.AttractionSummary;
import com.odorok.OdorokApplication.attraction.dto.response.item.ContentTypeSummary;
import com.odorok.OdorokApplication.attraction.repository.AttractionRepository;
import com.odorok.OdorokApplication.attraction.repository.ContentTypeRepository;
import com.odorok.OdorokApplication.draftDomain.Attraction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService{
    private final ContentTypeRepository contentTypeRepository;
    private final AttractionRepository attractionRepository;

    @Override
    public List<ContentTypeSummary> queryAllContentTypes() {
        return contentTypeRepository.findAll().stream()
                .map(c -> new ContentTypeSummary(c.getId(), c.getName())).toList();
    }

    @Override
    public List<AttractionSummary> queryRegionalAttractions(Integer sidoCode, Integer sigunguCode, Integer contentTypeId) {
        return attractionRepository.findBySidoCodeAndSigunguCodeAndContentTypeId(sidoCode, sigunguCode, contentTypeId)
                .stream().map(i ->{
                    AttractionSummary sum = new AttractionSummary();
                    sum.summarize(i);
                    return sum;
                }).toList();
    }

    @Override
    public AttractionDetail queryAttractionDetail(Long id) {
        Attraction attr = attractionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 '명소' 식별자 : " + id));
        return new AttractionDetail(attr.getOverview());
    }
}
