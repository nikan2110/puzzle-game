package telran.b7a.puzzleGames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.b7a.puzzleGames.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
