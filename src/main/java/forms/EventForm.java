package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Customer;
import domain.User;

public class EventForm {
	
	private int id;
	private String title;
	private Date startMoment;
	private Date finishMoment;
	private String description;
	private int numberMaxParticipant;
	private String sport;
	private String place;	
	private String address;
	private int price;
	
	private Customer customer;
	private User owner;
	
	public int getId() 
	{		
		return id;		
	}
	public void setId(int id) 
	{		
		this.id = id;		
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
	
	@Valid
	public Customer getCustomer() 
	{
		
		return customer;
		
	}
	public void setCustomer(Customer customer) 
	{
		
		this.customer = customer;
		
	}
	
	@Valid
	public User getOwner() 
	{
		
		return owner;
		
	}
	public void setOwner(User owner) 
	{
		
		this.owner = owner;
		
	}	
	
	public String getAddress() 
	{
		
		return address;
		
	}
	
	public void setAddress(String address) 
	{
		
		this.address = address;
		
	}
	
	@Min(0)
	public int getPrice() 
	{
		
		return price;
		
	}
	public void setPrice(int price) 
	{
		
		this.price = price;
		
	}	
	
	
	
	
}

