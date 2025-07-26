package com.odorok.OdorokApplication.security.repository;

import com.odorok.OdorokApplication.security.domain.Token;

public interface CustomTokenRepository {
    boolean isValidToken(String plain, String username, String type);
    Token findValidTokenByPlainAndUsername(String plain, String username, String type);
}
