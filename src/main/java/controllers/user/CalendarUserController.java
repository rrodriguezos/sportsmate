package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import services.EventService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;
import domain.Event;
import domain.Match;
import domain.Team;
import domain.Tournament;
import domain.User;
import forms.EventForm;
@Controller
@RequestMapping("/event/user/calendar")
public class CalendarUserController extends AbstractController
{
	
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private services.MatchService matchService;
	
	
	public CalendarUserController()
	{
		super();
	}
	
	
	@RequestMapping("/seeSportCenters")
	public ModelAndView seeCostumerList()
	{
		ModelAndView result;
		
		Collection<Customer> customers;
		
		customers=customerService.findAll();
		
		if(customers==null)
			new Throwable("no custumers");
		
		result=new ModelAndView("event/user/calendar/seeSportCenters");
		result.addObject("centers", customers);
		
		return result;
		
	}

	
	
	@RequestMapping("seeSportCenterCalendar")
	public ModelAndView seeSportCenterCalendar(@RequestParam int id)
	{
		
		ModelAndView result;
		Customer c;
		User u;
		Date actualDate;
		Collection<Event> eventsToRemove;
		
		actualDate = new Date();
		eventsToRemove = new ArrayList<Event>();
		
		if( id < 0 )
			new Throwable("Bad id");
		
		c=customerService.findOne(id);
		
		if(c==null)
			new Throwable("Bad customer");
		
		u=userService.findByPrincipal();
		
		
		if(c == null )
			new Throwable("Bad user");
			
		
		result = new ModelAndView("event/user/calendar/seeSportCenterCalendar");
		
		result.addObject("customer", c);
		
		List<Event> events=(List<Event>) eventService.findAllEventsCalendar(c.getId());
		Collection<Tournament> tournaments =tournamentService.findAllTournamentsCalendar(c.getId());
		
		Collections.sort(events, new Comparator<Event>() {
		    public int compare(Event m1, Event m2) {
		        return m1.getStartMoment().compareTo(m2.getStartMoment());
		    }
		});
		
		for(Event e: c.getEvents()){
			if(e.getStartMoment().before(actualDate)){
				eventsToRemove.add(e);
			}
		}
		
		c.getEvents().removeAll(eventsToRemove);
		
		
		
		result.addObject("events", c.getEvents());
		result.addObject("actualDate", actualDate);
		result.addObject("tournaments", c.getTournaments());
		result.addObject("userEvents", u.getEvents());
		
		return result;
	}
	
	@RequestMapping("joinEvent")
	public ModelAndView joinEvent(@RequestParam int id)
	{
		
		ModelAndView result;
		
		if(id < 0 )
			new Throwable("bad id for event");
		
		Event e=eventService.findOne(id);
		
		if(e==null)
			new Throwable("bad event from DB");
			
		
		User u=userService.findByPrincipal();
		
		if(u == null )
			new Throwable("bad user from DB");
		
		Collection<Event> events=u.getEvents();
		
		if(events.contains(e))
			new Throwable("The user is joined to this event, can't rejoint");
		
		Collection<User> users=e.getUsers();
		users.add(u);
		e.setUsers(users);
		eventService.save(e);
		
		events.add(e);
		
		
		u.setEvents(events);
		
		userService.save(u);
		
		return seeCostumerList();
			
		
		
		
	}
	
	@RequestMapping("seePerfilOfCustomer")
	public ModelAndView seePerfilOfCustomer(@RequestParam int id)
	{
		
		ModelAndView result = new ModelAndView("event/user/calendar/seePerfilOfCustomer");
		
		Customer customer= customerService.findOne(id);
		
		if (customer == null)
			new Throwable("opps you can try to get bad id?");
		
		result.addObject("customer", customer);
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId) {

		ModelAndView result;
		Event event;
		EventForm eventForm;
		Collection<User> users;
		Actor actor;
		Boolean estoyApuntado = false;

		event = eventService.findOne(eventId);
		eventForm = eventService.construct(event);
		users = userService.findAllUsersByEventId(eventId);
		actor = actorService.findByPrincipal();

		if (event.getUsers().contains(actor)) {
			estoyApuntado = true;
		}
		result = new ModelAndView("event/user/calendar/display");

		result.addObject("eventForm", eventForm);
		result.addObject("users", users);
		result.addObject("creationMoment", event.getCreationMoment());
		result.addObject("estoyApuntado", estoyApuntado);
		Date today = new Date(System.currentTimeMillis());
		Date finish = eventForm.getFinishMoment();
		result.addObject("today", today);
		result.addObject("finish", finish);
		int miId = actor.getId();
		result.addObject("miId", miId);

		if (actor instanceof User) {
			User user = (User) actor;
			result.addObject("user", user);
		}

		return result;

	}

	
	@RequestMapping(value = "/displayTournament", method = RequestMethod.GET)
	public ModelAndView displayTournament(@RequestParam int tournamentId) {
		ModelAndView result;
		Tournament tournament;
		Collection<Team> teams;
		Collection<Match> matches;

		tournament = tournamentService.findOne(tournamentId);
		teams = teamService.findAllTeamsByTournamentId(tournamentId);
		matches = matchService.findAllMatchesByTournament(tournament);

		result = new ModelAndView("event/user/calendar/displayTournament");
		//result.addObject("miId", customerService.findByPrincipal().getId());
		result.addObject("tournament", tournament);
		result.addObject("tournamentId", tournamentId);
		result.addObject("matches", matches);
		result.addObject("teams", teams);
		Boolean puedeEditar = true;
		Date now = new Date(System.currentTimeMillis());
		if (tournament.getStartMoment().before(now)) {
			puedeEditar = false;
		}
		result.addObject("puedeEditar", puedeEditar);
		return result;

	}


}
