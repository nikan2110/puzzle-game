package telran.b7a.puzzleGames.service;

import java.util.List;

import telran.b7a.puzzleGames.dto.AnswersDto;
import telran.b7a.puzzleGames.models.Puzzle;
import telran.b7a.puzzleGames.models.PuzzleResult;

public interface PuzzleService {

	Puzzle createPuzzle(Integer numOfItems);

	Puzzle getPuzzle(Integer puzzleId);

	Puzzle udpdatePuzzle(Integer puzzleId, Integer numOfItems);

	PuzzleResult postResult(Integer playerId, Integer puzzleId, AnswersDto results);
	
	PuzzleResult getResult(Integer resultId);
	
	List<PuzzleResult> getResults();
	
	PuzzleResult saveResultInFile(Integer resultId);
	
	void removePuzzle(Integer puzzleId);

}
