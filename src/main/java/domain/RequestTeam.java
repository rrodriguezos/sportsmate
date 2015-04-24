package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class RequestTeam extends DomainEntity{	
	
	//Constructors----------------------------------------------------------------------
	
	public RequestTeam() 
	{
		super();
	}
	
	private Date requestDate;
	private Boolean request;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getRequestDate() 
	{
		return requestDate;
	}
	
	public void setRequestDate(Date requestDate) 
	{
		this.requestDate = requestDate;
	}
	
	@NotNull
	public Boolean getRequest() 
	{
		return request;
	}
	
	public void setRequest(Boolean request) 
	{
		this.request = request;
	}
	
	//Relationships-------------------------------------------------------------------------
	
	private User user;
	private Team team;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Team getTeam() 
	{
		return team;
	}

	public void setTeam(Team team) 
	{
		this.team = team;
	}
	
	
}
