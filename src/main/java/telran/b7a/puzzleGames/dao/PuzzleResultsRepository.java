package telran.b7a.puzzleGames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.b7a.puzzleGames.models.PuzzleResult;

public interface PuzzleResultsRepository extends JpaRepository<PuzzleResult, Integer> {

}
