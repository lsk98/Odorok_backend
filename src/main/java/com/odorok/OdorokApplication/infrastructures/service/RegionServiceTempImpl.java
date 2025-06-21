package com.odorok.OdorokApplication.infrastructures.service;

import com.odorok.OdorokApplication.draftDomain.Sido;
import com.odorok.OdorokApplication.draftDomain.Sigungu;
import com.odorok.OdorokApplication.infrastructures.repository.SidoRepository;
import com.odorok.OdorokApplication.infrastructures.repository.SigunguRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceTempImpl implements RegionServiceTemp{
    private final SidoRepository sidoRepository;
    private final SigunguRepository sigunguRepository;

    @Override
    public Sido findSidoByShortExp(String sidoName) {
        // 서울, 전남 -> 서울특별시, 전라남도
        Optional<Sido> sido = sidoRepository.findByNameLike(sidoName);
        return sido.orElseThrow(() -> new RuntimeException("no such exp sido name : " + sidoName));
    }

    @Override
    public Sigungu findSigunguByShortExp(Integer sidoCode, String sigunguName) {
        Optional<Sigungu> sigungu = sigunguRepository.findByNameLikeAndSidoCode(sigunguName, sidoCode);
        return sigungu.orElseThrow(() -> new RuntimeException("no such exp sigungu name : " + sigunguName + " and sido code : " + sidoCode));
    }
}
