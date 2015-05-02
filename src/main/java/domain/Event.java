package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity{
	//Constructors----------------------------------------------------------------------
	public Event()
	{
		super();
	}	
	
	//Attributes-------------------------------------------------------------------------
	private String title;
	private Date creationMoment;
	private Date startMoment;
	private Date finishMoment;
	private String description;
	private int numberMaxParticipant;
	private String sport;
	private String place;
	private String address;
	private double price;
	
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
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartMoment() 
	{
		return startMoment;
	}
	public void setStartMoment(Date startMoment) {
		this.startMoment = startMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFinishMoment() {
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
	
	@Range(min=2, max=300)
	public int getNumberMaxParticipant() 
	{
		return numberMaxParticipant;
	}
	public void setNumberMaxParticipant(int numberMaxParticipant) 
	{
		this.numberMaxParticipant = numberMaxParticipant;
	}
	
	@NotBlank
	public String getSport() 
	{
		return sport;
	}
	public void setSport(String sport) 
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
	
	public String getAddress() 
	{
		
		return address;
		
	}
	public void setAddress(String address) 
	{
		
		this.address = address;
		
	}	

	public double getPrice() 
	{
		
		return price;
		
	}
	public void setPrice(double price) 
	{
		
		this.price = price;
		
	}

	//Relationships-------------------------------------------------------------------------
	private User owner;
	private Collection<User> users;
	private Customer customer;

	@Valid
	@ManyToOne(optional=true)
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner)
	{
		this.owner = owner;
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
	@ManyToOne(optional=true)
	public Customer getCustomer() 
	{
		return customer;
	}
	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}	
	
}
