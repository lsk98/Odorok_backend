package com.odorok.OdorokApplication.infrastructures.service;

import com.odorok.OdorokApplication.infrastructures.domain.Sido;
import com.odorok.OdorokApplication.infrastructures.domain.Sigungu;

public interface RegionServiceTemp {
    public Sido findSidoByShortExp(String sidoName);
    public Sigungu findSigunguByShortExp(Integer sidoCode, String sigunguName);
}
