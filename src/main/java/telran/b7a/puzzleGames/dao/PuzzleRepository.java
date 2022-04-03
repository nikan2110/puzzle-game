package telran.b7a.puzzleGames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.b7a.puzzleGames.models.Puzzle;

public interface PuzzleRepository extends JpaRepository<Puzzle, Integer> {

}
