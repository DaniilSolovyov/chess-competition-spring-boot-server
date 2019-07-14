package app.controller;

import app.domain.Player;
import app.repository.PlayerRepository;
import com.gmail.solovyov.daniil.monitoring.Monitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Monitoring("CREATE_PLAYER")
    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player player) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerRepository.create(player));
    }

    @Monitoring("FIND_ALL_PLAYERS")
    @GetMapping
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Monitoring("UPDATE_PLAYER_BY_ID")
    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@RequestBody Player player, @PathVariable("id") Integer id) {
        return playerRepository.existsById(id) ?
                ResponseEntity.ok().body(playerRepository.update(player)) :
                ResponseEntity.notFound().build();
    }

    @Monitoring("DELETE_PLAYER_BY_ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
