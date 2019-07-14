package app.repository;

import app.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private static final String CREATE_SCRIPT =
            "INSERT INTO PLAYER (SCHOOL_ID, SURE_NAME, RATING_VALUE, RATING_DELTA, RATING_UPDATE, GAMES_NUMBER) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDTE_SCRIPT = "";

    private static final String FIND_ALL_SCRIPT =
            "SELECT ID, SCHOOL_ID, SURE_NAME, RATING_VALUE, RATING_DELTA, RATING_UPDATE, GAMES_NUMBER FROM PLAYER";

//    private static final String FIND_TOP_FIVE_SCRIPT =
//            FIND_ALL_SCRIPT + " ORDER BY RATING_UPDATE DESC, RATING_DELTA DESC LIMIT 5";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Player create(Player player) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            int i = 0;
            PreparedStatement ps = connection.prepareStatement(CREATE_SCRIPT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(++i, player.getSchoolId());
            ps.setString(++i, player.getSureName());
            ps.setInt(++i, player.getRatingValue());
            ps.setInt(++i, player.getRatingDelta());
            ps.setTimestamp(++i, player.getRatingUpdate());
            ps.setInt(++i, player.getGamesNumber());
            return ps;
        }, keyHolder);

        player.setId((int) keyHolder.getKeys().get("id"));

        return player;
    }

    @Override
    public Player update(Player player) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }


    @Override
    public List<Player> findAll() {
        return jdbcTemplate.query(FIND_ALL_SCRIPT, new BeanPropertyRowMapper<>(Player.class));
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
