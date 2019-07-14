package app.repository;

import app.domain.Player;

import java.util.List;

public interface PlayerRepository {
    Player create(Player player);

    Player update(Player player);

    void deleteById(Integer id);

    List<Player> findAll();

    boolean existsById(Integer id);
}
