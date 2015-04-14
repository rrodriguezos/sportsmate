package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import services.UserService;
import domain.Customer;
import domain.User;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController 
{
	//Services----------------------------------------------------------	
	@Autowired
	private ActorService actorService;
	
	//Supported Services----------------------------------------------------------	
	@Autowired
	private CustomerService customerService ;
	
	@Autowired
	private UserService userService;
	
	
	//Search----------------------------------------------------------
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchActor(@RequestParam String keyword)
	{
		ModelAndView result;
		
		Collection<Customer> customers;
		Collection<User> users;
		
		customers = customerService.findByKeyword(keyword);
		users = userService.findByKeyword(keyword);
		
		result = new ModelAndView("actor/search");
		result.addObject("requestURI", "actor/search.do");
		result.addObject("customers", customers);
		result.addObject("users", users);
		
		return result;
	}

}


