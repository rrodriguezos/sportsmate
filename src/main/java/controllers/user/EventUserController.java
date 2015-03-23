package controllers.user;

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
import forms.EventForm;

@Controller
@RequestMapping("/event/user")
public class EventUserController extends AbstractController{
	
	// Services ---------------------------------------------------------------	
	@Autowired 
	private EventService eventService;


	// Constructors -----------------------------------------------------------
	public EventUserController()
	{
		
		super();
		
	}
		
	//Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list()
	{
		
		ModelAndView result;
		Collection<Event> events;
		
		events = eventService.findAllEventsByUserId();
		
		result = new ModelAndView("event/list");
		
		result.addObject("events", events);
		result.addObject("requestURI", "event/user/list.do");
		
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
	
	// Creation-----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() 
	{
		
		ModelAndView result;
		Event event;
		EventForm eventForm;

		event = eventService.create();
		eventForm = eventService.construct(event);

		result = createEditModelAndView(eventForm);
			
		return result;
		
	}
	
	// Ancillary methods---------------------------------------

	protected ModelAndView createEditModelAndView(EventForm eventForm) 
	{
		
		ModelAndView result;
		
		result = createEditModelAndView(eventForm, null);
		
		return result;
		
	}

	protected ModelAndView createEditModelAndView(EventForm eventForm, String message) 
	{
		
		ModelAndView result;
		Collection<String> sports;
			
		sports = eventService.sports();

		result = new ModelAndView("event/edit");
			
		result.addObject("eventForm", eventForm);
		result.addObject("sports", sports);
		result.addObject("message", message);
		

		return result;
	}		
	
}
