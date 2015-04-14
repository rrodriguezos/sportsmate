package controllers.customer;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.EventService;
import services.InvoiceService;
import services.TournamentService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;
import forms.CustomerForm;


@Controller
@RequestMapping("/profile/customer")
public class ProfileCustomerController extends AbstractController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	


//Display----------------------------------------------------------------------

@RequestMapping(value = "/display", method = RequestMethod.GET)
public ModelAndView display() {

	ModelAndView result;
	CustomerForm customerForm;
	Customer customer;
	Actor actor;


	Customer profile = customerService.findByPrincipal();
	profile = customerService.findOne(profile.getId());
	customerForm = customerService.construct(profile);
	actor = customerService.findByPrincipal();
	customer = customerService.findByPrincipal();	
	
	result = new ModelAndView("profile/display");		

	result.addObject("customerForm", customerForm);
	result.addObject("actor",actor);
	result.addObject("customer",customer);	
	result.addObject("requestURI", "profile/customer/display.do");
	

	return result;

}
		
//Edition----------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.GET)
public ModelAndView edit()
{

	ModelAndView result;
	Customer customer;
	CustomerForm customerForm;
	
	customer = customerService.findByPrincipal();
	customerForm = customerService.construct(customer);
	
	result = createEditModelAndView(customerForm);
	
	return result;
}
//Save --------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.POST, params= "save")
public ModelAndView save(@Valid CustomerForm customerForm, BindingResult binding)
{
	
	
	ModelAndView result;
	Customer customer;
	
	if(binding.hasErrors()){
		result = createEditModelAndView(customerForm);
	}else{
		try {
			
			customer = customerService.reconstructEdit(customerForm);
			customerService.save(customer);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(customerForm, "customer.commit.error");
		}
}
	return result;
}

//Delete-----------------------------------------------------------------------

@RequestMapping( value ="/edit", method = RequestMethod.POST , params = "delete")
public ModelAndView delete(CustomerForm customerForm, BindingResult binding)
{
	ModelAndView result;
	Customer customer;
	
	try {
		customer = customerService.reconstruct(customerForm);
		customerService.delete(customer);
		result = new ModelAndView("redirect:list.do");
	} catch (Throwable oops) {
		result = createEditModelAndView(customerForm, "customer.commit.error");
	}
	return result;


}

//The ancillary methods--------------------------------------------------------

protected ModelAndView createEditModelAndView(CustomerForm customerForm){
	ModelAndView result;
	
	result = createEditModelAndView(customerForm, null);
	
	return result;
}

protected ModelAndView createEditModelAndView(CustomerForm customerForm, String message)
{
	ModelAndView result;

	result = new ModelAndView("customer/edit");
	result.addObject("customerForm", customerForm );	
	result.addObject("message", message);
	
	return result;
}
}


