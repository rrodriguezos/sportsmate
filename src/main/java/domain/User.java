package domain;

import java.util.Collection;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes={@Index(columnList="name"),@Index(columnList="surname"),@Index(columnList="email")})
public class User extends Actor{
	
	//Constructors----------------------------------------------------------------------
	public User()
	{
		
		super();
		
	}	
	
	//Attributes-------------------------------------------------------------------------
	private Collection<Vote> votes;
	
	@Valid
	@NotNull
	@ElementCollection
	public Collection<Vote> getVotes() 
	{
		
		return votes;
		
	}

	public void setVotes(Collection<Vote> votes) 
	{
		
		this.votes = votes;
		
	}
	
	@Transient
	public Double getRating()
	{
		
		Double rating = 0.0;
		
		for(Vote itero : getVotes()){
			rating+=itero.getScore();
		}
				
		return rating;
		
	}	
	
		
	//Relationships----------------------------------------------------------------------
	
	private Collection<Friendship> frindships;
	private Collection<Event> events;
	private Collection<Event> eventsCreated;
	private Collection<Team> teams;
	private Collection<Team> teamsCreated;
	private Collection<Tournament> tournaments;
	private Collection<Tournament> tournamentsCreated;
	private Collection<RequestTeam> requests;	

	@Valid
	@NotNull
	@OneToMany(mappedBy="user")
	public Collection<Friendship> getFriendships() 
	{
		
		return frindships;
		
	}

	public void setFriendships(Collection<Friendship> Friendships) 
	{
		
		this.frindships = Friendships;
		
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Event> getEvents() 
	{
		
		return events;
		
	}	
	
	public void setEvents(Collection<Event> events) 
	{
		
		this.events = events;
		
	}	
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="owner")
	public Collection<Event> getEventsCreated() 
	{
		
		return eventsCreated;
		
	}

	public void setEventsCreated(Collection<Event> eventsCreated) 
	{
		
		this.eventsCreated = eventsCreated;
		
	}

	@Valid
	@NotNull
	@ManyToMany
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
	@Size(min=0,max=10)
	@OneToMany(mappedBy="captain")
	public Collection<Team> getTeamsCreated() 
	{
		
		return teamsCreated;
		
	}

	public void setTeamsCreated(Collection<Team> teamsCreated)
	{
		
		this.teamsCreated = teamsCreated;
		
	}

	@Valid 
	@OneToMany(mappedBy="user")
	public Collection<Tournament> getTournaments() 
	{
		
		return tournaments;
		
	}

	public void setTournaments(Collection<Tournament> tournaments) 
	{
		
		this.tournaments = tournaments;
		
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Tournament> getTournamentsCreated() 
	{
		
		return tournamentsCreated;
		
	}

	public void setTournamentsCreated(Collection<Tournament> tournamentsCreated) 
	{
		
		this.tournamentsCreated = tournamentsCreated;
		
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="user")
	public Collection<RequestTeam> getRequests() 
	{
		
		return requests;
		
	}

	public void setRequests(Collection<RequestTeam> requests) 
	{
		
		this.requests = requests;
		
	}
	
}
