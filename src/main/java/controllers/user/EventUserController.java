package controllers.user;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.UserService;
import controllers.AbstractController;
import domain.Event;
import domain.User;
import forms.EventForm;

@Controller
@RequestMapping("/event/user")
public class EventUserController extends AbstractController{
	
	// Services ---------------------------------------------------------------	
	@Autowired 
	private EventService eventService;

	@Autowired
	private UserService userService;

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
	
	//Display-----------------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId)
	{
		
		ModelAndView result;
		Event event;
		Collection<User> users;
		
		event = eventService.findOne(eventId);
		users = userService.findAllUsersByEventId(eventId);
		
		result = new ModelAndView("event/display");
		
		result.addObject("event", event);
		result.addObject("users", users);		
		
		return result;
		
	}
	
	// Creation---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() 
	{
		
		ModelAndView result;
		Event event;
		EventForm eventForm;

		event = eventService.create();
		eventForm = eventService.construct(event);

		result = createEditModelAndView(eventForm);
		result.addObject("requestURI", "event/user/edit.do");
			
		return result;
		
	}
	
	// Edition---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int eventId) {
		ModelAndView result;
		Event event;
		EventForm eventForm;	
		Collection<String> places;
		
		places = eventService.places();
		event = eventService.findOneToEdit(eventId);	
		
		eventForm = eventService.construct(event);
		if(!places.contains(event.getPlace())){
			
			eventForm.setOtherSportCenter(event.getPlace());
		}

		result = createEditModelAndView(eventForm);

		return result;
	}

	// Save-----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid EventForm eventForm, BindingResult binding)
	{
		
		ModelAndView result;
		Event event;
			
		if(binding.hasErrors()){
			result = createEditModelAndView(eventForm);
		} else {
			try{
				
				event = eventService.reconstruct(eventForm);
				
				if(event.getOwner() != null){
					event.getUsers().add(event.getOwner());
				}			
				
				String defectValue = "------";
				
				if(event.getSport().equals(defectValue)){
					
					result = createEditModelAndView(eventForm, "event.sport.null");
					
				}else if(event.getPlace().equals(defectValue)){
					
					result = createEditModelAndView(eventForm, "event.place.null");
					
				}else{
					eventService.save(event);	
					
					result = new ModelAndView("redirect:list.do");
				}
				
				
			}catch(Throwable oops){
					
				result = createEditModelAndView(eventForm,"event.commit.error");			
			}
		}
		
		return result;		
		
	}	
	
	// Delete---------------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid EventForm eventForm, BindingResult binding) 
	{
		
		ModelAndView result;
		Event event;
		try {
			
			event = eventService.reconstruct(eventForm);
			
			eventService.delete(event);
			
			result = new ModelAndView("redirect:list.do");
			
		} catch (Throwable oops) {
			result = createEditModelAndView(eventForm, "event.commit.error");
		}
		return result;
		
	}
	
	
	// Ancillary methods---------------------------------------------------

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
		Collection<String> places;
		sports = eventService.sports();
		places = eventService.places();

		result = new ModelAndView("event/edit");
			
		result.addObject("eventForm", eventForm);
		result.addObject("sports", sports);
		result.addObject("places", places);
		result.addObject("message", message);		

		return result;
	}		
	
}


