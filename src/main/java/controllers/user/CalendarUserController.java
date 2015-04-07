package controllers.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.EventService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;
import domain.Customer;
import domain.Event;
import domain.Tournament;
import domain.User;
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
		
	
		
		
		
		
		result.addObject("events", c.getEvents());
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

	


}
