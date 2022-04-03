package telran.b7a.puzzleGames.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "examResults")
@EqualsAndHashCode(of = { "id" })
public class PuzzleResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5778811570194019560L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	@ManyToOne
	Player player;
	@ElementCollection(targetClass = Integer.class)
	Set<Integer> missingItems;

	public PuzzleResult(Player player, Set<Integer> missingItems) {
		this.player = player;
		this.missingItems = missingItems;
	}

	public PuzzleResult(Set<Integer> missingItems) {
		this.missingItems = missingItems;
	}

	@Override
	public String toString() {
		return "PuzzleResult [id=" + id + ", player=" + player + ", missingItems=" + missingItems + "]";
	}
	
	

}
