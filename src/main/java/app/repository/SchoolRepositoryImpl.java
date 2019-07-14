package app.repository;

import app.domain.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchoolRepositoryImpl implements SchoolRepository {

    private static final String FIND_ALL_SCRIPT = "SELECT ID, NAME FROM SCHOOL";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SchoolRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<School> findAll() {
        return jdbcTemplate.query(FIND_ALL_SCRIPT, new BeanPropertyRowMapper<>(School.class));
    }
}
