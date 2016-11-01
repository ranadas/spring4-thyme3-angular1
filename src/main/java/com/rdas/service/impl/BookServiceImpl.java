package com.rdas.service.impl;

import com.rdas.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rdas on 16/10/2016.
 */
@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DataSource dataSource;

    @Override
    public String findById(long id) {
        return null;
    }

    @Override
    public String findByName(String name) {
        return null;
    }

    @Override
    public List findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String SQL = "SELECT * FROM author";
        List<String> authors = jdbcTemplate.query(SQL, (rs, rowNum) -> {
            Integer id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            return String.format("%s, %s", first_name, last_name);
        });
        return authors;
    }
}
