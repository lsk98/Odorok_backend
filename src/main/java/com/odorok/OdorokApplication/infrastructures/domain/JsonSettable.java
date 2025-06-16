package com.odorok.OdorokApplication.infrastructures.domain;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

public interface JsonSettable {
    public void setWithJson(JsonObject object);
    public default  LocalDateTime convertDateType(String date) {
        // 20210308064716
        // 01234567890123
        return LocalDateTime.of(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(4, 6)),
                Integer.parseInt(date.substring(6, 8)),
                Integer.parseInt(date.substring(8, 10)),
                Integer.parseInt(date.substring(10, 12)),
                Integer.parseInt(date.substring(12)));
    }
}
