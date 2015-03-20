package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Event;
import domain.User;

@Service
@Transactional
public class EventService {
	
	//Managed repository -----------------------------------------------------
	@Autowired
	private EventRepository eventRepository;
	
	// Supporting services ---------------------------------------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	// Constructors-----------------------------------------------------------
	public EventService()
	{
		
		super();		
		
	}
	
	// Simple CRUD methods ---------------------------------------------------
	
	public Collection<Event>  findAll()
	{
		
		Collection<Event> all;
		
		all = eventRepository.findAll();
		
		return all;
		
	}
	
	public Event findOne(int eventId) 
	{
		
		Event result;
		
		result = eventRepository.findOne(eventId);
		
		return result;
		
	}
	
	public void save(Event event)
	{
		
		Assert.notNull(event);
		
		eventRepository.save(event);
		
	}
	//Other business methods ------------------------------------------------
	
	public void checkPrincipalByUser(Event event){
		UserAccount userAccount;
		int userAccountId1;		
		User user;
		int userAccountId2;
		
		userAccount = LoginService.getPrincipal();
		userAccountId1 = userAccount.getId();
		user = event.getOwner();
		userAccountId2 = user.getUserAccount().getId();
		
		Assert.isTrue(userAccountId1 == userAccountId2);
	}
	
	public void checkPrincipalByCustomer(Event event){
		UserAccount userAccount;
		int userAccountId1;		
		Customer customer;
		int userAccountId2;
		
		userAccount = LoginService.getPrincipal();
		userAccountId1 = userAccount.getId();
		customer = event.getCustomer();
		userAccountId2 = customer.getUserAccount().getId();
		
		Assert.isTrue(userAccountId1 == userAccountId2);
		
	}
	
	public Event findOneToEditByUserId()
	{
		
		Event result;
		User user;
		int userId;
		
		user = userService.findByPrincipal();
		userId = user.getId();
		result = eventRepository.findUserToEditByUserId(userId);	
		
		checkPrincipalByUser(result);
		
		return result;
		
	}
	
	public Event findOneToEditByCustomerId()
	{
		
		Event result;
		Customer customer;
		int customerId;
		
		customer = customerService.findByPrincipal();
		customerId = customer.getId();
		result = eventRepository.findCustomerToEditByCustomerId(customerId);	
		
		checkPrincipalByCustomer(result);
		
		return result;
		
	}
	
	public Collection<Event> findAllEventsByUserId()
	{
		
		Collection<Event> all;
		User user;
		int userId;
		
		user = userService.findByPrincipal();
		userId = user.getId();
		all = eventRepository.findAllEventsByUserId(userId);
		
		return all;
		
	}
	
	public Collection<Event> findAllEventsByCustomerId()
	{
		
		Collection<Event> all;
		Customer customer;
		int customerId;
		
		customer = customerService.findByPrincipal();
		customerId = customer.getId();
		all = eventRepository.findAllEventsByCustomerId(customerId);
		
		return all;
		
	}
}



	


	



	
	

	