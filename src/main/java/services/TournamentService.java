package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import domain.Event;
import domain.Tournament;
import domain.Match;
import domain.Team;
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
	@Autowired
	private TeamService teamService;

	public Collection<Tournament> findAll() {
		Collection<Tournament> all;

		all = tournamentRepository.findAll();

		return all;
	}

	public Tournament findOne(int tournamentId) {
		Tournament result;

		result = tournamentRepository.findOne(tournamentId);
		return result;
	}

	public Tournament findOneToJoin(int tournamentId) {

		Tournament result;

		result = tournamentRepository.findOne(tournamentId);

		return result;

	}

	public Tournament create() {

		Tournament tournament;
		Actor actor;
		User owner;
		Customer customer;
		Collection<Team> teams;
		Collection<Match> matches;
		Date creationMoment;

		tournament = new Tournament();
		actor = actorService.findByPrincipal();
		teams = new ArrayList<Team>();
		matches = new ArrayList<Match>();
		creationMoment = new Date(System.currentTimeMillis() - 10000);

		if (actor instanceof User) {

			owner = userService.findByPrincipal();

			tournament.setUser(owner);

		} else if (actor instanceof Customer) {

			customer = customerService.findByPrincipal();

			tournament.setCustomer(customer);
			tournament.setPlace(tournament.getCustomer().getNameCenter());

		}

		tournament.setCreationMoment(creationMoment);
		tournament.setMatches(matches);
		tournament.setTeams(teams);

		return tournament;

	}

	public void save(Tournament tournament) {
		System.out.println("entra en el save");
		User owner;
		Customer customer = customerService.findOneFromPlaceString(tournament
				.getPlace());
		Tournament result;
		tournament.setCustomer(customer);
		Date now = new Date(System.currentTimeMillis());
		Assert.isTrue(tournament.getStartMoment().after(now));
		Assert.isTrue(tournament.getFinishMoment().after(now));
		Assert.isTrue(tournament.getStartMoment().compareTo(
				tournament.getFinishMoment()) < 0);
		tournament.setCreationMoment(new Date(
				System.currentTimeMillis() - 10000));
		result = tournamentRepository.saveAndFlush(tournament);

		if (actorService.findByPrincipal() instanceof User) {

			owner = userService.findByPrincipal();
			if (tournament.getId() == 0) {
				owner.getTournamentsCreated().add(result);
				owner.getTournaments().add(result);
			}
			userService.save(owner);

		} else if (actorService.findByPrincipal() instanceof Customer) {

			customer = customerService.findByPrincipal();
			customer.getTournaments().add(result);

			customerService.save(customer);
		}

	}
	
	public void saveRounds(Tournament tournament) {
		System.out.println("entra en el save");
		User owner;
		Customer customer = customerService.findOneFromPlaceString(tournament
				.getPlace());
		Tournament result;
		tournament.setCustomer(customer);
		Date now = new Date(System.currentTimeMillis());
		Assert.isTrue(tournament.getStartMoment().before(now));
		Assert.isTrue(tournament.getStartMoment().compareTo(
				tournament.getFinishMoment()) < 0);
		tournament.setCreationMoment(new Date(
				System.currentTimeMillis() - 10000));
		result = tournamentRepository.saveAndFlush(tournament);

		if (actorService.findByPrincipal() instanceof User) {

			owner = userService.findByPrincipal();
			if (tournament.getId() == 0) {
				owner.getTournamentsCreated().add(result);
				owner.getTournaments().add(result);
			}
			userService.save(owner);

		} else if (actorService.findByPrincipal() instanceof Customer) {

			customer = customerService.findByPrincipal();
			customer.getTournaments().add(result);

			customerService.save(customer);
		}

	}

	public Tournament saveJoin(Tournament tournament) {

		tournament = tournamentRepository.save(tournament);

		return tournament;
	}

	public void delete(TournamentForm tournamentForm) {
		Tournament tournament = reconstruct(tournamentForm);
		User owner;
		Customer customer;

		checkPrincipalByActor(tournament);
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.format(currentDate);

		if (actorService.findByPrincipal() instanceof User) {

			Assert.isTrue(currentDate.compareTo(tournament.getFinishMoment()) > 0);
			owner = userService.findByPrincipal();
			owner.getTournamentsCreated().remove(tournament);

			userService.save(owner);

		} else if (actorService.findByPrincipal() instanceof Customer) {

			customer = customerService.findByPrincipal();
			Assert.isTrue(currentDate.compareTo(tournament.getFinishMoment()) > 0);
			customer.getTournaments().remove(tournament);
			customerService.save(customer);
		}
		tournamentRepository.delete(tournament);
	}

	// Other business methods
	// ------------------------------------------------------

	public void checkPrincipalByActor(Tournament tournament) {

		Actor actor;
		User owner;
		Customer customer;

		actor = actorService.findByPrincipal();

		if (actor instanceof User) {
			owner = userService.findByPrincipal();
			Assert.isTrue(tournament.getUser().equals(owner));
		} else if (actor instanceof Customer) {
			customer = customerService.findByPrincipal();
			Assert.isTrue(tournament.getCustomer().equals(customer));
		}

	}

	public Tournament findOneToEdit(int tournamentId) {

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

	public Collection<Tournament> findAllTournamentByPrincipal() {

		Collection<Tournament> result;
		Collection<Team> teams;

		teams = teamService.findAllTeamsByUserId();
		result = new ArrayList<Tournament>();

		for (Team t : teams) {
			for (Tournament tour : t.getTournaments()) {
				if (!result.contains(tour)) {
					result.add(tour);
				}
			}
		}

		return result;

	}

	public Collection<Tournament> findAllTournamentsCreatedByCustomerId() {
		Collection<Tournament> all;
		Customer customer;
		int customerId;

		customer = customerService.findByPrincipal();
		customerId = customer.getId();
		all = tournamentRepository
				.findAllTournamentsCreatedByCustomerId(customerId);

		return all;

	}

	public Collection<String> sports() {

		Collection<String> all;

		all = new ArrayList<String>();

		all.add("FOOTBALL");
		all.add("TENNIS");
		all.add("BASKETBALL");
		all.add("FUTSAL");
		all.add("RACE");
		all.add("PADDLE");
		all.add("FOOTBALL_7");

		return all;

	}

	public Collection<String> places() {
		Collection<String> allPlaces = new ArrayList<String>();
		Collection<Customer> allCustomers;
		String nameCenter;

		allCustomers = customerService.findAll();
		String alternativePlace = "Other";
		allPlaces.add(alternativePlace);
		for (Customer c : allCustomers) {
			nameCenter = c.getNameCenter();
			allPlaces.add(nameCenter);
		}
		return allPlaces;

	}

	public TournamentForm construct(Tournament tournament) {

		TournamentForm result;

		result = new TournamentForm();
		result.setUserFee(tournament.getUserFee());
		result.setId(tournament.getId());
		result.setTitle(tournament.getTitle());
		result.setAdvertised(tournament.isAdvertised());
		result.setStartMoment(tournament.getStartMoment());
		result.setFinishMoment(tournament.getFinishMoment());
		result.setDescription(tournament.getDescription());
		result.setNumberOfTeams(tournament.getNumberOfTeams());
		result.setSport(tournament.getSport());
		result.setPrize(tournament.getPrize());
		if (tournament.getUser() instanceof User) {
			result.setUser(tournament.getUser());
		} else if (tournament.getCustomer() instanceof Customer) {
			result.setCustomer(tournament.getCustomer());
			result.setPlace(tournament.getPlace());
		}

		return result;

	}

	public Tournament reconstruct(TournamentForm tournamentForm) {
		if (userService.findByPrincipal() != null) {
			if ((!tournamentForm.getPlace().equals("Other") && tournamentForm
					.getOtherSportCenter().length() > 0)
					|| (tournamentForm.getPlace().equals("Other") && tournamentForm
							.getOtherSportCenter().length() == 0)) {
				throw new IllegalArgumentException("PACO");
			}
		}
		Tournament tournament;

		if (tournamentForm.getId() != 0) {
			tournament = tournamentRepository.findOne(tournamentForm.getId());

			checkPrincipalByActor(tournament);
		} else {
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
		tournament.setUserFee(tournamentForm.getUserFee());
		tournament.setPrize(tournamentForm.getPrize());
		if (tournament.getUser() instanceof User) {
			if (tournamentForm.getOtherSportCenter() != "") {
				tournament.setPlace(tournamentForm.getOtherSportCenter());
			} else {
				tournament.setPlace(tournamentForm.getPlace());
			}
		}

		if (tournament.getCustomer() instanceof Customer) {
			tournament.setPlace(tournamentForm.getPlace());
		}
		tournament.setCreationMoment(new Date(
				System.currentTimeMillis() - 10000));
		return tournament;

	}

	public Collection<Tournament> findAllTournamentsCalendar(int id) {

		Collection<Tournament> result;

		if (id < 0)
			new Throwable("Bad id customer");

		result = tournamentRepository.finAllEventsCalendar(id);

		if (result == null)
			new Throwable("Bad Tournaments from customer");

		return result;

	}

	public void joinTournament(Tournament tournament, Team team) {

		User user;

		user = userService.findByPrincipal();

		Assert.isTrue(team.getCaptain().equals(user));

		tournament.getTeams().add(team);
		user.getTournaments().add(tournament);
		team.getTournaments().add(tournament);

		if (!team.getUsers().contains(user)) {
			team.getUsers().add(user);
			user.getTeams().add(team);
		}

		saveJoin(tournament);
		teamService.saveJoin(team);
		userService.save(user);

	}

	public void DisjoinTournament(Tournament tournament) {

		User user;
		Team team = null;

		user = userService.findByPrincipal();

		for (Team t : tournament.getTeams()) {
			if (user.getTeams().contains(t)) {
				team = t;
			}
		}

		Assert.isTrue(team.getCaptain().equals(user));

		tournament.getTeams().remove(team);
		user.getTournaments().remove(tournament);
		team.getTournaments().remove(tournament);

		saveJoin(tournament);
		teamService.saveJoin(team);
		userService.save(user);
	}

	public Collection<Tournament> findAllTournamentByUser() {
		Collection<Tournament> all;
		Collection<Tournament> createdByUser;
		Collection<Tournament> myTournaments;
		User user;
		
		all = findAll();
		user = userService.findByPrincipal();
		myTournaments = new ArrayList<Tournament>();
		createdByUser = tournamentRepository.findAllTournamentsCreatedByUserId(user.getId());
		
		for (Tournament itero : all) {
			for (Team t : user.getTeams()) {
				if (itero.getTeams().contains(t)) {
					myTournaments.add(itero);
				}
			}
		}
		
		for(Tournament itero : createdByUser){
			if(!myTournaments.contains(itero)){
				myTournaments.add(itero);
			}
		}
		
		return myTournaments;
	}

}
