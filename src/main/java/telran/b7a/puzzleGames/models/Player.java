package telran.b7a.puzzleGames.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "players")
@EqualsAndHashCode(of = {"id"})
public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2110886752213771480L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@OneToMany(mappedBy = "player")
	Set<PuzzleResult> results;
	
	public Player(String name, LocalDate birthDate, Set<PuzzleResult> results) {
		this.name = name;
		this.birthDate = birthDate;
		this.results = results;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
	
	

}
