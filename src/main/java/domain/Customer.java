package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "name"), @Index(columnList = "surname"),
		@Index(columnList = "email") })
public class Customer extends Actor {
	// Constructors----------------------------------------------------------------------
	public Customer() 
	{
		
		super();
		
	}

	// Attributes-------------------------------------------------------------------------
	private String cif;
	private String street;
	private int zip;
	private String provinceCenter;
	private String city;
	private String nameCenter;
	private String phoneCenter;
	private String emailCenter;
	private String web;
	private Collection<Vote> votes;

	@NotBlank
	@Size(min = 9, max = 9)
	public String getCif()
	{
		
		return cif;
		
	}

	public void setCif(String cif) 
	{
		
		this.cif = cif;
		
	}

	@NotBlank
	public String getStreet() 
	{
		
		return street;
		
	}

	public void setStreet(String street)
	{
		
		this.street = street;
		
	}

	@Range(min = 0, max = 99999)
	public int getZip()
	{
		
		return zip;
		
	}

	public void setZip(int zip)
	{
		
		this.zip = zip;
		
	}

	@NotBlank
	public String getProvinceCenter() 
	{
		
		return provinceCenter;
		
	}

	public void setProvinceCenter(String provinceCenter)
	{
		
		this.provinceCenter = provinceCenter;
		
	}

	@NotBlank
	public String getCity() 
	{
		
		return city;
		
	}

	public void setCity(String city) 
	{
		
		this.city = city;
		
	}

	@NotBlank
	public String getNameCenter() 
	{
		
		return nameCenter;
		
	}

	public void setNameCenter(String nameCenter) 
	{
		
		this.nameCenter = nameCenter;
		
	}

	@NotBlank
	@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
	public String getPhoneCenter()
	{
		
		return phoneCenter;
		
	}

	public void setPhoneCenter(String phoneCenter)
	{
		
		this.phoneCenter = phoneCenter;
		
	}

	@NotBlank
	@Email
	public String getEmailCenter() 
	{
		
		return emailCenter;
		
	}

	public void setEmailCenter(String emailCenter)
	{
		
		this.emailCenter = emailCenter;
		
	}

	@URL
	public String getWeb() 
	{
		
		return web;
		
	}

	public void setWeb(String web)
	{
		
		this.web = web;
		
	}

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

	

	// Relationships-------------------------------------------------------------------------
	private Collection<Event> events;
	private Collection<Tournament> tournaments;
	private Collection<Invoice> invoices;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "customer")
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
	@OneToMany(mappedBy = "customer")
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
	@OneToMany(mappedBy = "customer")
	public Collection<Invoice> getInvoices()
	{
		
		return invoices;
		
	}

	public void setInvoices(Collection<Invoice> invoices) 
	{
		
		this.invoices = invoices;
		
	}

}
