package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM usertable.new_table";

        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User saveUser(User user) {
        String sql = "INSERT INTO usertable.new_table (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }
    // добавленный метод
    public void deleteById(int id) {
        String sql = "DELETE FROM usertable.new_table WHERE id=?";
        jdbc.update(sql, id);
    }
}
