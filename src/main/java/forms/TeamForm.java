package forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class TeamForm {

	private int id;
	private String name;
	private int maxNumber;

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

}
