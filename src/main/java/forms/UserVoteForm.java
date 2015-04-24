package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;

@Access(AccessType.PROPERTY)
public class UserVoteForm {
	
	private int id;
	private String name;
	private Integer score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
