package telran.b7a.puzzleGames.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.puzzleGames.dao.PlayerRepository;
import telran.b7a.puzzleGames.dto.NewPlayerDto;
import telran.b7a.puzzleGames.exceptions.PlayerNotFoundException;
import telran.b7a.puzzleGames.models.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

	PlayerRepository playerRepository;

	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public Player addPlayer(NewPlayerDto newPlayer) {
		Player player = new Player(newPlayer.getName(), newPlayer.getBirthDate(), new HashSet<>());
		return playerRepository.save(player);
	}

	@Override
	public Player getPlayer(Integer playerId) {
		return findPlayer(playerId);
	}

	@Override
	public Player updatePlayer(Integer playerId, String newName) {
		Player player = findPlayer(playerId);
		player.setName(newName);
		return playerRepository.save(player);
	}

	@Override
	public void removePlayer(Integer playerId) {
		Player player = findPlayer(playerId);
		playerRepository.delete(player);

	}

	private Player findPlayer(Integer playerId) {
		return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
	}

}
