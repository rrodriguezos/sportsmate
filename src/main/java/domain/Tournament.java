package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Tournament extends DomainEntity {
	// Constructors----------------------------------------------------------------------
	public Tournament() {
		super();
	}

	// Attributes-------------------------------------------------------------------------
	private boolean advertised;
	private String title;
	private Date creationMoment;
	private Date startMoment;
	private Date finishMoment;
	private String description;
	private String sport;
	private Double userFee;
	private String place;
	private int numberOfTeams;
	private Double prize;

	public boolean isAdvertised() {
		return advertised;
	}

	public void setAdvertised(boolean advertised) {
		this.advertised = advertised;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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
	public Date getStartMoment() {
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

	public void setFinishMoment(Date finishMoment) {
		this.finishMoment = finishMoment;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Min(2)
	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Double getPrize() {
		return prize;
	}

	public void setPrize(Double prize) {
		this.prize = prize;
	}

	// Relationships-------------------------------------------------------------------------
	private Collection<Match> matches;
	private User user;
	private Customer customer;
	private Collection<Team> teams;
	private Team winner;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Match> getMatches() {
		return matches;
	}

	public void setMatches(Collection<Match> matches) {
		this.matches = matches;
	}

	@Valid
	@ManyToOne(optional = true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@ManyToOne(optional = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Valid
	@NotNull
	@ManyToMany(cascade = { CascadeType.ALL })
	public Collection<Team> getTeams() {
		return teams;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	@Min(0)
	@NotNull
	@Digits(fraction = 2, integer = 4)
	public Double getUserFee() {
		return userFee;
	}

	public void setUserFee(Double userFee) {
		this.userFee = userFee;
	}

}
