
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
	Collection<Tournament> all;
	
	all = tournamentRepository.findAll();
	
	return all;
}
public Tournament findOne(int tournamentId) {
	Tournament result;
	
	result = tournamentRepository.findOne(tournamentId);
	
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
	miliseconds = System.currentTimeMillis()-3;
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

public void save(Tournament tournament)
{
	
	User owner;
	Customer customer;
	Tournament aux;
	
	Assert.notNull(tournament);
	Assert.isTrue(tournament.getStartMoment().compareTo(tournament.getFinishMoment()) < 0);
	
	aux = tournamentRepository.save(tournament);
	
	if(actorService.findByPrincipal() instanceof User){
		
		owner = (User) actorService.findByPrincipal();
		if (tournament.getId() == 0) {
			owner.getTournamentsCreated().add(aux);
			owner.getTournaments().add(aux);
		}
		userService.save(owner);
		
	}else if(actorService.findByPrincipal() instanceof Customer){
		
		customer = (Customer)actorService.findByPrincipal();
		customer.getTournaments().add(aux);
		customerService.save(customer);
	}
	
}
public void delete(Tournament tournament)
{
	User owner;
	Customer customer;
	
	Assert.notNull(tournament);
	checkPrincipalByActor(tournament);
	
	if(actorService.findByPrincipal() instanceof User){
		
		owner = (User)actorService.findByPrincipal();
		owner.getTournamentsCreated().remove(tournament);
		tournamentRepository.delete(tournament);
		userService.save(owner);
		
	}else if(actorService.findByPrincipal() instanceof Customer){
		
		customer = (Customer)actorService.findByPrincipal();
		customer.getTournaments().remove(tournament);
		tournamentRepository.delete(tournament);
		customerService.save(customer);			
	}
	

	
}

//Other business methods ------------------------------------------------------

public void checkPrincipalByActor(Tournament tournament)
{
	
	Actor actor;
	User owner;
	Customer customer;
	
	actor = actorService.findByPrincipal();
	
	if(actor instanceof User){
		owner = (User)actor;
		Assert.isTrue(tournament.getUser().equals(owner));
	}else if(actor instanceof Customer){
		customer = (Customer)actor;
		Assert.isTrue(tournament.getCustomer().equals(customer));
	}		
	
}

public Tournament findOneToEdit(int tournamentId)
{
	
	Tournament tournament;
	
	tournament = tournamentRepository.findOne(tournamentId);	
	
	checkPrincipalByActor(tournament);
	
	return tournament;
	
}

public Collection<Tournament> findAllTournamentsCreatedByUserId() {
	
	Collection<Tournament> all;
	User user;
	int userId;
	
	user = userService.findByPrincipal();
	userId = user.getId();
	all = tournamentRepository.findAllTournamentsCreatedByUserId(userId);
	
	return all;
}
public Collection<Tournament> findAllTournamentsCreatedByCustomerId()
{	
	Collection<Tournament> all;
	Customer customer;
	int customerId;
	
	customer = customerService.findByPrincipal();
	customerId = customer.getId();
	all = tournamentRepository.findAllTournamentsCreatedByCustomerId(customerId);
	
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

public Collection<String> places()
{
	
	Collection<String> all;
	
	all = new ArrayList<String>();
	
	all.add("IndorClub"); all.add("SportClub"); all.add("Place 3"); all.add("Place 4"); all.add("Place 5");
	all.add("Place 6"); all.add("Place 7"); all.add("Place 8"); all.add("Place 9"); all.add("Other center");
	
	
	return all;
	
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
	result.setMatches(tournament.getMatches());
	result.setTeams(tournament.getTeams());
	
	if(tournament.getUser() instanceof User){
		result.setUser(tournament.getUser());
	}else if(tournament.getCustomer() instanceof Customer){
		result.setCustomer(tournament.getCustomer());
	}
	
	
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
	tournament.setMatches(tournamentForm.getMatches());
	tournament.setTeams(tournamentForm.getTeams());
	
	if(tournament.getUser() instanceof User){
		if(tournamentForm.getOtherSportCenter()!= ""){
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


public Collection<Tournament> findAllTournamentsCalendar(int id) {
	
	
	Collection<Tournament> result;
	
	if ( id < 0)
		new Throwable("Bad id customer");
	
	result=tournamentRepository.finAllEventsCalendar(id);
	
	if(result==null)
		new Throwable("Bad Tournaments from customer");
	
	
	
	return result;

}











}