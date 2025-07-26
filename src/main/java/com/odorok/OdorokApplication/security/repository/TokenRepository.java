package com.odorok.OdorokApplication.security.repository;

import com.odorok.OdorokApplication.security.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TokenRepository extends JpaRepository<Token, Long>, CustomTokenRepository {
    List<Token> findByUsernameAndIsExpired(String username, Boolean isExpired);
    List<Token> findByUsernameAndIsExpiredAndType(String username, Boolean isExpired, String type);
}
