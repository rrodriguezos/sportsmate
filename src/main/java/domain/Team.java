package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Team extends DomainEntity{
	//Constructors----------------------------------------------------------------------
	public Team()
	{
		super();
	}	
	
	//Attributes-------------------------------------------------------------------------
	private String name;
	private int maxNumber;
	
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
		
	//Relationships-------------------------------------------------------------------------
	
	private User captain;
	private Collection<User> users;
	private Collection<Tournament> tournaments;
	private Collection<Match> matchs;
	private Collection<Match> winners;

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
	
	@Valid
	@NotNull
	@ManyToMany
	public Collection<User> getUsers() 
	{
		return users;
	}
	public void setUsers(Collection<User> users) 
	{
		this.users = users;
	}
	
	@Valid
	@NotNull
	@ManyToMany
	public Collection<Tournament> getTournaments() 
	{
		return tournaments;
	}
	public void setTournaments(Collection<Tournament> tournaments) 
	{
		this.tournaments = tournaments;
	}
	
	@Valid
	@NotNull
	@ManyToMany
	public Collection<Match> getMatchs() {
		return matchs;
	}
	public void setMatchs(Collection<Match> matchs) {
		this.matchs = matchs;
	}
	
	@OneToMany(mappedBy="winner")
	public Collection<Match> getWinners() {
		return winners;
	}
	public void setWinners(Collection<Match> winners) {
		this.winners = winners;
	}	
	
	
	
}
