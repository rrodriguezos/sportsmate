package controllers.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import services.CustomerService;
import domain.Customer;
@Controller
@RequestMapping("/event/user/calendar")
public class CalendarUserController extends AbstractController
{
	
	
	
	@Autowired
	private CustomerService customerService;
	
	
	
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



	


}
