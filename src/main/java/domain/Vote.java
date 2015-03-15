package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class Vote {
	//Constructors----------------------------------------------------------------------
	public Vote()
	{
		super();
	}	
	
	//Attributes-------------------------------------------------------------------------
	private int score;
	private  String nameUser;
	
	@Range(min=0,max=5)
	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	
	@NotBlank
	public String getNameUser() 
	{
		return nameUser;
	}
	public void setNameUser(String nameUser) 
	{
		this.nameUser = nameUser;
	}
	
	
		
}
