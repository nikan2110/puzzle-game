package telran.b7a.puzzleGames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class PuzzleNotFoundException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = 5635370307230854544L;
	public PuzzleNotFoundException(Integer id) {
		super("Puzzle with id " + id + " not exist");
	}


}
