
package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TournamentRepository;
import domain.Actor;
import domain.Customer;
import domain.Match;
import domain.Team;
import domain.Tournament;
import domain.User;
import forms.TournamentForm;
@Service
@Transactional
public class TournamentService {
@Autowired
	private TournamentRepository tournamentRepository;
@Autowired
	private CustomerService customerService;
@Autowired
	private UserService userService;
@Autowired
	private ActorService actorService;

public Collection<Tournament>  findAll(){
return tournamentRepository.findAll();
}
public Tournament findOne(Integer valueOf) {
return tournamentRepository.findOne(valueOf);
}

public void save(Tournament tournament)
{
	
	User organiser;
	Customer customer;
	Tournament aux;
	
	Assert.notNull(tournament);
	Assert.isTrue(tournament.getStartMoment().compareTo(tournament.getFinishMoment()) < 0);
	
	aux = tournamentRepository.save(tournament);
	
	if(actorService.findByPrincipal() instanceof User){
		
		organiser = (User) actorService.findByPrincipal();
		if (tournament.getId() == 0) {
			organiser.getTournamentsCreated().add(aux);
			organiser.getTournaments().add(aux);
		}
		userService.save(organiser);
		
	}else if(actorService.findByPrincipal() instanceof Customer){
		
		customer = (Customer)actorService.findByPrincipal();
		customer.getTournaments().add(aux);
		customerService.save(customer);
	}
	
}

public TournamentForm construct(Tournament tournament)
{
	
	TournamentForm result;
	
	result = new TournamentForm();
	
	result.setId(tournament.getId());
	result.setTitle(tournament.getTitle());
	result.setAdvertised(tournament.isAdvertised());
	result.setStartMoment(tournament.getStartMoment());
	result.setFinishMoment(tournament.getFinishMoment());
	result.setDescription(tournament.getDescription());
	result.setNumberOfTeams(tournament.getNumberOfTeams());
	result.setSport(tournament.getSport());
	result.setPlace(tournament.getPlace());
	result.setPrize(tournament.getPrize());
	
	
	return result;
	
}

public Tournament reconstruct(TournamentForm tournamentForm)
{
	
	Tournament tournament;
	
	if(tournamentForm.getId() != 0){
		tournament = tournamentRepository.findOne(tournamentForm.getId());
		
		checkPrincipalByActor(tournament);
	}else{
		tournament = this.create();
	}
	
	tournament.setId(tournamentForm.getId());
	tournament.setAdvertised(tournamentForm.isAdvertised());
	tournament.setStartMoment(tournamentForm.getStartMoment());
	tournament.setFinishMoment(tournamentForm.getFinishMoment());
	tournament.setTitle(tournamentForm.getTitle());
	tournament.setDescription(tournamentForm.getDescription());
	tournament.setNumberOfTeams(tournamentForm.getNumberOfTeams());
	tournament.setSport(tournamentForm.getSport());
	
	if(tournament.getUser() instanceof User){
		if(!tournamentForm.getOtherSportCenter().isEmpty()){
			tournament.setPlace(tournamentForm.getOtherSportCenter());
		}else{
			tournament.setPlace(tournamentForm.getPlace());	
		}
	}
	
	if(tournament.getCustomer() instanceof Customer){
		tournament.setPlace(tournamentForm.getPlace());
	}	
	
	return tournament;
	
}
public Collection<Tournament> findAllTournamentsByCustomerId()
{	
	Collection<Tournament> all;
	Customer customer;
	int customerId;
	
	customer = customerService.findByPrincipal();
	customerId = customer.getId();
	all = tournamentRepository.findAllTournamentsByCustomerId(customerId);
	
	return all;
	
}
public Collection<Tournament> findAllTournamentsByUserId() {
	Collection<Tournament> all;
	User user;
	int userId;
	
	user = userService.findByPrincipal();
	userId = user.getId();
	all = tournamentRepository.findAllTournamentsByUserId(userId);
	
	return all;
}
public Collection<Tournament> findAllTournamentsCalendar(int id) {
	
	
	Collection<Tournament> result;
	
	if ( id < 0)
		new Throwable("Bad id customer");
	
	result=tournamentRepository.finAllEventsCalendar(id);
	
	if(result==null)
		new Throwable("Bad Tournaments from customer");
	
	
	
	return result;

}

public Tournament create()
{
	
	Tournament tournament;
	Actor actor;
	User owner;
	Customer customer;
	Collection<Team> teams;
	Collection<Match> matches;
	Date creationMoment;
	long miliseconds;
	
	tournament = new Tournament();
	actor = actorService.findByPrincipal();
	teams = new ArrayList<Team>();
	matches = new ArrayList<Match>();
	miliseconds = System.currentTimeMillis()-10;
	creationMoment = new Date(miliseconds);
	
	if(actor instanceof User){
		
		owner = (User)actor;
		
		tournament.setUser(owner);
		
	}else if(actor instanceof Customer){
		
		customer = (Customer)actor;
		
		tournament.setCustomer(customer);
		tournament.setPlace(tournament.getCustomer().getNameCenter());
	
	}
	
	tournament.setCreationMoment(creationMoment);
	tournament.setMatches(matches);
	tournament.setTeams(teams);
	
	return tournament;
	
}

public void delete(Tournament tournament)
{
	User organiser;
	Customer customer;
	
	Assert.notNull(tournament);
	checkPrincipalByActor(tournament);
	Assert.isTrue(tournament.getTeams().size()>=4);
	
	if(actorService.findByPrincipal() instanceof User){
		
		organiser = (User)actorService.findByPrincipal();
		organiser.getTournamentsCreated().remove(tournament);
		userService.save(organiser);
		
	}else if(actorService.findByPrincipal() instanceof Customer){
		
		customer = (Customer)actorService.findByPrincipal();
		customer.getTournaments().remove(tournament);
		customerService.save(customer);			
	}
	
	tournamentRepository.delete(tournament);
	
}

public void checkPrincipalByActor(Tournament tournament)
{
	
	Actor actor;
	User organiser;
	Customer customer;
	
	actor = actorService.findByPrincipal();
	
	if(actor instanceof User){
		organiser = (User)actor;
		Assert.isTrue(tournament.getUser().equals(organiser));
	}else if(actor instanceof Customer){
		customer = (Customer)actor;
		Assert.isTrue(tournament.getCustomer().equals(customer));
	}		
	
}

public Collection<String> places()
{
	
	Collection<String> all;
	
	all = new ArrayList<String>();
	
	all.add("IndorClub"); all.add("SportClub"); all.add("Place 3"); all.add("Place 4"); all.add("Place 5");
	all.add("Place 6"); all.add("Place 7"); all.add("Place 8"); all.add("Place 9"); all.add("Other center");
	
	
	return all;
	
}

public Collection<String> sports()
{
	
	Collection<String> all;
	
	all = new ArrayList<String>();
	
	all.add("FOOTBALL"); all.add("TENNIS"); all.add("BASKETBALL"); all.add("FUTSAL");
	all.add("RACE"); all.add("PADDLE"); all.add("FOOTBALL_7"); 
	
	return all;
	
}

}