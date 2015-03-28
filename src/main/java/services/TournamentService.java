
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TournamentRepository;
import domain.Customer;
import domain.Event;
import domain.Tournament;
import domain.User;
@Service
@Transactional
public class TournamentService {
@Autowired
	private TournamentRepository tournamentRepository;
@Autowired
	private CustomerService customerService;
@Autowired
	private UserService userService;
public Collection<Tournament>  findAll(){
return tournamentRepository.findAll();
}
public Tournament findOne(Integer valueOf) {
return tournamentRepository.findOne(valueOf);
}
public Tournament save(Tournament tournament){
return tournamentRepository.save(tournament);
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
}