package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="Match_")
public class Match extends DomainEntity{
	//Constructors----------------------------------------------------------------------
	public Match()
	{
		super();
	}	
	
	//Attributes-------------------------------------------------------------------------
	private String title;
	private Date creationMoment;
	private Date startMoment;
	private Date finishMoment;
	private String description;	
	
	@NotBlank
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreationMoment() 
	{
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) 
	{
		this.creationMoment = creationMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartMoment() 
	{
		return startMoment;
	}
	public void setStartMoment(Date startMoment) 
	{
		this.startMoment = startMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFinishMoment() 
	{
		return finishMoment;
	}
	public void setFinishMoment(Date finishMoment)
	{
		this.finishMoment = finishMoment;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}		
		
	//Relationships-------------------------------------------------------------------------
	private Team winner;
	private Collection<Team> teams;
	private Tournament tournament;	

	
	
	@ManyToOne(optional=true)
	public Team getWinner() 
	{
		return winner;
	}
	public void setWinner(Team winner) 
	{
		this.winner = winner;
	}
	
	@Valid
	@NotNull
	@ManyToMany(cascade = CascadeType.ALL)
	@Size(min=2,max=2)
	public Collection<Team> getTeams() 
	{
		return teams;
	}
	public void setTeams(Collection<Team> teams) 
	{
		this.teams = teams;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Tournament getTournament() 
	{
		return tournament;
	}
	public void setTournament(Tournament tournament) 
	{
		this.tournament = tournament;
	}
	
	
	
	
	
}
