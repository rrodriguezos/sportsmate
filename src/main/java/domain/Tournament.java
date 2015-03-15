package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Tournament extends DomainEntity{
	//Constructors----------------------------------------------------------------------	
	public Tournament()
	{
		super();
	}
	
	//Attributes-------------------------------------------------------------------------
	private boolean advertised;
	private String title;
	private Date creationMoment;
	private Date startMoment;
	private Date finishMoment;
	private String description;
	private Sport sport;
	private String place;
	private int numberOfTeams;
	private Double price;
	
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
	@Past
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
	@Past
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
	
	
	public Double getPrice() 
	{
		return price;
	}
	public void setPrice(Double price) 
	{
		this.price = price;
	}	
		
	//Relationships-------------------------------------------------------------------------
	private Match match;
	private User user;
	private Customer customer;
	private Collection<Team> teams;	

	@Valid
	@NotNull
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	public Match getMatch() 
	{
		return match;
	}
	public void setMatch(Match match) 
	{
		this.match = match;
	}
	
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
	public Customer getCustomer() 
	{
		return customer;
	}
	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
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
	
	
	

}
