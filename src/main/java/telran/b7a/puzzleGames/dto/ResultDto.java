package telran.b7a.puzzleGames.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResultDto {
	
	Integer id;
	PlayerDto player;
	Set<Integer> missingItems;

}
