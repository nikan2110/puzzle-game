package telran.b7a.puzzleGames.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import telran.b7a.puzzleGames.configuration.ResultPdfExporter;
import telran.b7a.puzzleGames.dto.AnswersDto;
import telran.b7a.puzzleGames.dto.NewPlayerDto;
import telran.b7a.puzzleGames.dto.PlayerDto;
import telran.b7a.puzzleGames.dto.PuzzleDto;
import telran.b7a.puzzleGames.dto.ResultDto;
import telran.b7a.puzzleGames.models.PuzzleResult;
import telran.b7a.puzzleGames.service.PlayerService;
import telran.b7a.puzzleGames.service.PuzzleService;

@RestController
public class PuzzleGamesController {

	PlayerService playerService;
	PuzzleService puzzleService;
	ModelMapper modelMapper;

	@Autowired
	public PuzzleGamesController(PlayerService playerService, PuzzleService puzzleService, ModelMapper modelMapper) {
		this.playerService = playerService;
		this.puzzleService = puzzleService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/player")
	public PlayerDto addPlayer(@RequestBody NewPlayerDto newPlayer) {
		return modelMapper.map(playerService.addPlayer(newPlayer), PlayerDto.class);
	}

	@GetMapping("/player/players/")
	public PlayerDto getPlayer(@RequestParam("id") Integer id) {
		return modelMapper.map(playerService.getPlayer(id), PlayerDto.class);
	}

	@PutMapping("/player/players/")
	public PlayerDto updatePlayer(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		return modelMapper.map(playerService.updatePlayer(id, name), PlayerDto.class);
	}

	@DeleteMapping("/player/players/")
	public void removePlayer(@RequestParam("id") Integer id) {
		playerService.removePlayer(id);
	}

	// *******************************************PUZZLE CONTROLLER*******************************************

	@PostMapping("/puzzle")
	public PuzzleDto createPuzzle(@RequestParam("numOfItems") Integer numOfItems) {
		return modelMapper.map(puzzleService.createPuzzle(numOfItems), PuzzleDto.class);
	}

	@PostMapping("/puzzle/results/")
	public ResultDto postResult(@RequestParam("playerId") Integer playerId, @RequestParam("puzzleId") Integer puzzleId,
			@RequestBody AnswersDto results) {
		PuzzleResult result = puzzleService.postResult(playerId, puzzleId, results);
		return modelMapper.map(result, ResultDto.class);
	}

	@GetMapping("/puzzle/puzzles/")
	public PuzzleDto getPuzzle(@RequestParam("id") Integer puzzleId) {
		return modelMapper.map(puzzleService.getPuzzle(puzzleId), PuzzleDto.class);
	}

	@GetMapping("/puzzle/results/")
	public ResultDto getPuzzleResultById(@RequestParam("id") Integer puzzleResultId) {
		return modelMapper.map(puzzleService.getResult(puzzleResultId), ResultDto.class);
	}

	@GetMapping("/puzzle/results/save")
	public ResponseEntity<byte[]> demo(@RequestParam("id") Integer puzzleResultId) {
		PuzzleResult result = puzzleService.saveResultInFile(puzzleResultId);
		byte[] data = SerializationUtils.serialize(result);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
				ContentDisposition.attachment().filename("result.txt").build().toString());
		return ResponseEntity.ok().headers(httpHeaders).body(data);

	}

	@GetMapping("/puzzle/results/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("results.pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Results_" + LocalDate.now() + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<PuzzleResult> result = puzzleService.getResults();
        ResultPdfExporter exporter = new ResultPdfExporter(result);
        exporter.export(response);
	}

	@PutMapping("/puzzle/puzzles/")
	public PuzzleDto updatePuzzle(@RequestParam("id") Integer puzzleId,
			@RequestParam("numOfItems") Integer numOfItems) {
		return modelMapper.map(puzzleService.udpdatePuzzle(puzzleId, numOfItems), PuzzleDto.class);
	}

	@DeleteMapping("/puzzle/puzzles/")
	public void removePuzzle(@RequestParam("id") Integer puzzleId) {
		puzzleService.removePuzzle(puzzleId);
	}

}
