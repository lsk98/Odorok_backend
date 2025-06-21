package com.odorok.OdorokApplication.infrastructures.service;

import com.odorok.OdorokApplication.draftDomain.Sido;
import com.odorok.OdorokApplication.draftDomain.Sigungu;

public interface RegionServiceTemp {
    public Sido findSidoByShortExp(String sidoName);
    public Sigungu findSigunguByShortExp(Integer sidoCode, String sigunguName);
}
