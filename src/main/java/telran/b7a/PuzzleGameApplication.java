package telran.b7a;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import telran.b7a.puzzleGames.dao.PlayerRepository;
import telran.b7a.puzzleGames.dao.PuzzleRepository;
import telran.b7a.puzzleGames.models.Player;
import telran.b7a.puzzleGames.models.Puzzle;

@EnableScheduling
@SpringBootApplication
public class PuzzleGameApplication implements CommandLineRunner {

	PlayerRepository playerRepository;
	PuzzleRepository puzzleRepository;

	@Autowired
	public PuzzleGameApplication(PlayerRepository playerRepository, PuzzleRepository puzzleRepository) {
		this.playerRepository = playerRepository;
		this.puzzleRepository = puzzleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PuzzleGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Player player1 = new Player(1000, "John", LocalDate.of(1991, 8, 10), new HashSet<>());
//		Player player2 = new Player(2000, "Peter", LocalDate.of(1990, 05, 9), new HashSet<>());
//		playerRepository.save(player1);
//		playerRepository.save(player2);
//		Puzzle puzzle1 = new Puzzle(1100, 10);
//		Puzzle puzzle2 = new Puzzle(2100, 5);
//		puzzleRepository.save(puzzle1);
//		puzzleRepository.save(puzzle2);

	}

}
