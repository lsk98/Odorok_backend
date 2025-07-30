package com.odorok.OdorokApplication.security.service;


import com.odorok.OdorokApplication.security.dto.SignupRequest;

public interface SignupService {
    void signup(SignupRequest request);
}
