package telran.b7a.puzzleGames.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class NewPlayerDto {
	
	String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate birthDate;

}
