package forms;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import domain.User;

public class TeamForm {

	private int id;
	private String name;
	private int maxNumber;
	private User captain;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	@NotBlank
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	@Range(min=1, max=20)
	public int getMaxNumber() 
	{
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) 
	{
		this.maxNumber = maxNumber;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public User getCaptain() 
	{
		return captain;
	}
	public void setCaptain(User captain) 
	{
		this.captain = captain;
	}

}
