package com.bht.repositories.impl;

import com.bht.models.User;
import com.bht.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int nextIdValue() {
        return jdbcTemplate.queryForObject(
                "SELECT IDENT_CURRENT('[User]') + 1",
                Integer.class
        );
    }

    @Override
    public boolean addUser(User user) {

        String sql = "INSERT INTO [User](username, password, email, gender, hasAvatar) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getHasAvatar());

        return true;
    }


    @Override
    public boolean updateUser(User user) {

        String sql = "UPDATE [User] SET " +
                "username = ? , " +
                "password = ? , " +
                "email = ? , " +
                "gender = ? , " +
                "hasAvatar = ? " +
                "WHERE id = ? ";

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getHasAvatar(),
                user.getId());

        return true;
    }


    @Override
    public boolean deleteUser(int id) {

        String sql = "DELETE FROM [User] WHERE id = ?";

        jdbcTemplate.update(sql, id);

        return true;
    }


    @Override
    public User getUserById(int id) {

        String sql = "SELECT * FROM [User] WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql,                // sql statement
                new Object[]{id},   // according to ? in sql
                new UserRowMapper() // RowMapper for User (implements RowMapper<User>)
        );
    }


    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM [User] ";

        return jdbcTemplate.query(
                sql,                // sql statement
                new Object[]{},     // according to ? in sql
                new UserRowMapper() // RowMapper for User (implements RowMapper<User>)
        );
    }


    public class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setGender(resultSet.getBoolean("gender"));
            user.setHasAvatar(resultSet.getBoolean("hasAvatar"));

            return user;
        }
    }
}
