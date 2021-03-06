package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import services.EventService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;
import domain.Event;
import domain.Invoice;
import domain.User;
import forms.EventForm;

@Controller
@RequestMapping("/event/customer")
public class EventCustomerController extends AbstractController{
	// Services ---------------------------------------------------------------	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------
	public EventCustomerController() {

		super();

	}

	// Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Event> events;
		boolean my = true;

		events = eventService.findAllEventsCreatedByCustomerId();

		result = new ModelAndView("event/list");

		result.addObject("events", events);
		result.addObject("my", my);
		result.addObject("requestURI", "event/customer/list.do");

		return result;

	}
	
	
	@RequestMapping(value = "/listOtherEvents", method = RequestMethod.GET)
	public ModelAndView listOtherEvents() {

		ModelAndView result;
		Collection<Event> events;
		Date actualDate;
		Collection<Event> eventsToRemove;
		boolean remnant = true;

		actualDate = new Date();
		eventsToRemove = new ArrayList<Event>();
		events = eventService.findAllOtherEvents();
		
		for(Event e: events){
			if(e.getStartMoment().before(actualDate)){
				eventsToRemove.add(e);
			}
		}
		
		events.removeAll(eventsToRemove);

		result = new ModelAndView("event/list");

		result.addObject("events", events);
		result.addObject("remnant", remnant);
		result.addObject("requestURI", "event/customer/listOthersEvents.do");

		return result;

	}
	
	@RequestMapping(value = "/listFootballEvents", method = RequestMethod.GET)
	public ModelAndView listFootballEvents() {

		ModelAndView result;
		Collection<Event> events;
		Date actualDate;
		Collection<Event> eventsToRemove;
		boolean football = true;
		
		actualDate = new Date();
		eventsToRemove = new ArrayList<Event>();
		events = eventService.findAllFootballEvents();
		
		for(Event e: events){
			if(e.getStartMoment().before(actualDate)){
				eventsToRemove.add(e);
			}
		}
		
		events.removeAll(eventsToRemove);

		result = new ModelAndView("event/list");

		result.addObject("events", events);
		result.addObject("football", football);
		result.addObject("requestURI", "event/customer/listFootballEvents.do");

		return result;

	}
	
	@RequestMapping(value = "/listTennisEvents", method = RequestMethod.GET)
	public ModelAndView listTennisEvents() {

		ModelAndView result;
		Collection<Event> events;
		Date actualDate;
		Collection<Event> eventsToRemove;
		boolean tennis = true;

		actualDate = new Date();
		eventsToRemove = new ArrayList<Event>();
		events = eventService.findAllTennisEvents();
		
		for(Event e: events){
			if(e.getStartMoment().before(actualDate)){
				eventsToRemove.add(e);
			}
		}
		
		events.removeAll(eventsToRemove);

		result = new ModelAndView("event/list");

		result.addObject("events", events);
		result.addObject("tennis", tennis);
		result.addObject("requestURI", "event/customer/listTennisEvents.do");

		return result;

	}
	
	@RequestMapping(value = "/listPaddleEvents", method = RequestMethod.GET)
	public ModelAndView listPaddleEvents() {

		ModelAndView result;
		Collection<Event> events;
		Date actualDate;
		Collection<Event> eventsToRemove;
		boolean paddle = true;

		actualDate = new Date();
		eventsToRemove = new ArrayList<Event>();
		events = eventService.findAllPaddleEvents();
		
		for(Event e: events){
			if(e.getStartMoment().before(actualDate)){
				eventsToRemove.add(e);
			}
		}
		
		events.removeAll(eventsToRemove);

		result = new ModelAndView("event/list");

		result.addObject("events", events);
		result.addObject("paddle", paddle);
		result.addObject("requestURI", "event/customer/listPaddleEvents.do");

		return result;

	}

	// Display------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId) {

		ModelAndView result;
		Event event;
		EventForm eventForm;
		Collection<User> users;
		Actor actor;
		Date currentDate;

		event = eventService.findOne(eventId);
		eventForm = eventService.construct(event);		
		actor = actorService.findByPrincipal();
		users = userService.findAllUsersByEventId(eventId);
		currentDate = new Date();
		
		result = new ModelAndView("event/display");		

		result.addObject("eventForm", eventForm);
		result.addObject("users", users);
		result.addObject("creationMoment", event.getCreationMoment());
		
		if(actor instanceof Customer){
			Customer customer = (Customer)actor;
			result.addObject("customer", customer);
		}
		
		result.addObject("currentDate", currentDate);

		return result;

	}

	// Creation-----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		/* for debtors */
		
		Customer e = customerService.findByPrincipal();
		if( e.isDebtor()){
			
			ModelAndView result;
			
			Collection<Invoice> invoices=customerService.getAllInvoices();
			
			result=new ModelAndView("customer/seeInvoices");
			result.addObject("invoices", invoices);
			return result;
		}
		
		
		
		
		/* -------------*/

		ModelAndView result;
		Event event;
		EventForm eventForm;

		event = eventService.create();
		eventForm = eventService.construct(event);

		result = createEditModelAndView(eventForm);
		result.addObject("requestURI", "event/customer/edit.do");

		return result;

	}
	
	// Edition---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int eventId) {
		ModelAndView result;
		Event event;
		EventForm eventForm;
		Collection<User> users;
		Date currentDate;

		event = eventService.findOneToEdit(eventId);
		eventForm = eventService.construct(event);
		users = userService.findAllUsersByEventId(eventId);
		currentDate = new Date();

		result = createEditModelAndView(eventForm);
		
		result.addObject("users", users);
		result.addObject("currentDate", currentDate);

		return result;
	}

	// Save-----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEC")
	public ModelAndView save(@Valid EventForm eventForm, BindingResult binding) 
	{
		
		ModelAndView result;
		Event event;

		if (binding.hasErrors()) {
			result = createEditModelAndView(eventForm);
		} else {
			try {

				event = eventService.reconstruct(eventForm);
				
				eventService.save(event);

				result = new ModelAndView("redirect:list.do");				
				
			} catch (Throwable oops) {
				System.out.println(oops);
				if(oops instanceof TransactionSystemException){
					result = createEditModelAndView(eventForm, "event.price.error");
				}else if(oops instanceof IllegalArgumentException){
					result = createEditModelAndView(eventForm, "event.date.error");
				}else{
					
					result = createEditModelAndView(eventForm, "event.commit.error");	
				}				
			}
		}
		return result;
	}
	
	// Delete---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deleteEC")
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

	// Ancillary methods---------------------------------------

	protected ModelAndView createEditModelAndView(EventForm eventForm) {

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


