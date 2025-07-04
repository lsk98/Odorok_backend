package com.odorok.OdorokApplication.region.service;

import com.odorok.OdorokApplication.region.repository.SidoRepository;
import com.odorok.OdorokApplication.region.repository.SigunguRepository;
import com.odorok.OdorokApplication.region.dto.response.item.SidoSummary;
import com.odorok.OdorokApplication.region.dto.response.item.SigunguSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService{
    private final SidoRepository sidoRepository;
    private final SigunguRepository sigunguRepository;


    @Override
    public List<SidoSummary> queryAllSidos() {
        return sidoRepository.findAll().stream().map(sido -> new SidoSummary(sido.getName(), sido.getCode())).toList();
    }

    @Override
    public List<SigunguSummary> queryAllSigunguOf(Integer sidoCode) {
        return sigunguRepository.findBySidoCode(sidoCode).stream().map(sigungu -> new SigunguSummary(sigungu.getName(), sigungu.getCode())).toList();
    }
}
