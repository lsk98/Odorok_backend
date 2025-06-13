package com.odorok.OdorokApplication.commons;

import java.util.Map;

public interface RestApiCaller {
    public String doGet(Map<String, String> parameters);
    public String doPost(Map<String, String> parameters, String content);
}
