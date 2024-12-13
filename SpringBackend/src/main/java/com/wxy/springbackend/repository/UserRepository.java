package com.wxy.springbackend.repository;


import com.wxy.springbackend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("user_name"));
        return user;
    };

    public int save(User user) {
        String sql = "INSERT INTO wxy_user (password, user_name) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                user.getPassword(),
                user.getUsername());
    }

    public User findByUsername(String userName) {
        String sql = "SELECT user_id, password, user_name FROM wxy_user WHERE user_name = ?";
        return jdbcTemplate.query(sql, new Object[]{userName}, userRowMapper)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public User findByUserID(Integer userID) {
        String sql = "SELECT user_id, password, user_name FROM wxy_user WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userID}, userRowMapper)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public int resetPasswordByUserID(Integer userid, String password) {
        String sql = "UPDATE wxy_user SET password = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, password, userid);
    }

}
