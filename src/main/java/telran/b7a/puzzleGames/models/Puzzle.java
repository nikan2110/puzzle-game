package telran.b7a.puzzleGames.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "puzzles")
@EqualsAndHashCode(of = {"id"})
public class Puzzle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4760319043705338416L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@Setter
	Integer numOfItems;
	
	
	public Puzzle(Integer numOfItems) {
		this.numOfItems = numOfItems;
	}
	
	

}
