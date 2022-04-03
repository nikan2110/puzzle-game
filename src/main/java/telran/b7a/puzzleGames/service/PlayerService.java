package telran.b7a.puzzleGames.service;

import telran.b7a.puzzleGames.dto.NewPlayerDto;
import telran.b7a.puzzleGames.models.Player;

public interface PlayerService {
	
	Player addPlayer(NewPlayerDto newPlayer);
	
	Player getPlayer(Integer playerId);
	
	Player updatePlayer(Integer playerId, String newName);
	
	void removePlayer(Integer playerId);

}
