package app.repository;

import app.domain.School;

import java.util.List;

public interface SchoolRepository {
    List<School> findAll();
}
