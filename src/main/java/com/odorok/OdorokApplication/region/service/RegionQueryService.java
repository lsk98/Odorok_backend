package com.odorok.OdorokApplication.region.service;

import com.odorok.OdorokApplication.region.dto.response.item.SidoSummary;
import com.odorok.OdorokApplication.region.dto.response.item.SigunguSummary;

import java.util.List;

public interface RegionQueryService {
    List<SidoSummary> queryAllSidos();
    List<SigunguSummary> queryAllSigunguOf(Integer sidoCode);
}
