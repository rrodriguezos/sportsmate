package forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Customer;
import domain.Match;
import domain.Sport;
import domain.Team;
import domain.User;

public class TournamentForm {
	
	private int id;
	
	private boolean advertised;
	private String title;
	private Date creationMoment;
	private Date startMoment;
	private Date finishMoment;
	private String description;
	private Sport sport;
	private String place;
	private int numberOfTeams;
	private Double prize;
	
	private Customer customer;
	private User user;
	
	private Collection<Match> matches;
	private Collection<Team> teams;
	
	private String otherSportCenter;
	
	
	public int getId() 
	{		
		return id;		
	}
	public void setId(int id) 
	{		
		this.id = id;		
	}
	
	public boolean isAdvertised() 
	{
		return advertised;
	}
	public void setAdvertised(boolean advertised) 
	{
		this.advertised = advertised;
	}
	
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
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	@Valid
	@NotNull
	public Sport getSport() 
	{
		return sport;
	}
	public void setSport(Sport sport) 
	{
		this.sport = sport;
	}
	
	@NotBlank
	public String getPlace() 
	{
		return place;
	}
	public void setPlace(String place) 
	{
		this.place = place;
	}
	
	@Min(2)
	public int getNumberOfTeams() {
		return numberOfTeams;
	}
	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}
	
	
	public Double getPrize() 
	{
		return prize;
	}
	public void setPrize(Double prize) 
	{
		this.prize = prize;
	}	
	
	public String getOtherSportCenter() 
	{
		
		return otherSportCenter;
		
	}
	public void setOtherSportCenter(String otherSportCenter) 
	{
		
		this.otherSportCenter = otherSportCenter;
		
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
	@OneToMany(cascade=CascadeType.ALL)
	public Collection<Match> getMatches() 
	{
		return matches;
	}
	public void setMatches(Collection<Match> matches) 
	{
		this.matches = matches;
	}
	
	@Valid
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Valid
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	

}