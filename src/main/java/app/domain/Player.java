package app.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Player {
    private Integer id;
    private Integer schoolId;
    private String sureName;
    private Integer ratingValue;
    private Integer ratingDelta;
    private Integer gamesNumber;
    private Timestamp ratingUpdate;
}
