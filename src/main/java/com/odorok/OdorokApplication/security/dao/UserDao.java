package com.odorok.OdorokApplication.security.dao;

import com.odorok.OdorokApplication.security.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {
    // ---------- 테스트 용 -------------
    private final JdbcTemplate jdbc;

    public int updateRefreshToken(String email, String refreshToken){
        String sql = "UPDATE users SET refresh = ? WHERE email = ?";
        return jdbc.update(sql, refreshToken, email);
    }

    public User selectByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbc.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setName(rs.getString("name"));
            user.setRefresh(rs.getString("refresh"));
            return user;
        });
    }

    public int save(User user) {
        String sql = "INSERT INTO users (email, password, nickname, name) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, user.getEmail(), user.getPassword(), user.getNickname(), user.getName());
    }

}
