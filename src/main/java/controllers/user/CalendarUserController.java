package controllers.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import services.CustomerService;
import services.EventService;
import services.TournamentService;
import domain.Customer;
import domain.Event;
import domain.Tournament;
@Controller
@RequestMapping("/event/user/calendar")
public class CalendarUserController extends AbstractController
{
	
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EventService eventService;
	
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
		
		if( id < 0 )
			new Throwable("Bad id");
		
		c=customerService.findOne(id);
		
		if(c==null)
			new Throwable("Bad customer");
		
		result = new ModelAndView("event/user/calendar/seeSportCenterCalendar");
		
		result.addObject("customer", c);
		
		Collection<Event> events=eventService.findAllEventsCalendar(c.getId());
		Collection<Tournament> tournaments =tournamentService.findAllTournamentsCalendar(c.getId());
		
		
		result.addObject("events", c.getEvents());
		result.addObject("tournaments", c.getTournaments());
		
		return result;
	}

	


}
