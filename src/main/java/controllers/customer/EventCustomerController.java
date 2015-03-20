package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;

import controllers.AbstractController;
import domain.Event;

@Controller
@RequestMapping("/event/customer")
public class EventCustomerController extends AbstractController{
	// Services ---------------------------------------------------------------	
	@Autowired 
	private EventService eventService;


	// Constructors -----------------------------------------------------------
	public EventCustomerController()
	{
			
		super();
			
	}
			
	//Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list()
	{
			
		ModelAndView result;
		Collection<Event> events;
			
		events = eventService.findAllEventsByCustomerId();
			
		result = new ModelAndView("event/list");
			
		result.addObject("events", events);
		result.addObject("requestURI", "event/customer/list.do");
			
		return result;
			
	}
		
	//Display------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId)
	{
			
		ModelAndView result;
		Event event;
			
		event = eventService.findOne(eventId);
			
		result = new ModelAndView("event/display");
			
		result.addObject("event", event);
			
			
		return result;
			
	}
	
}
