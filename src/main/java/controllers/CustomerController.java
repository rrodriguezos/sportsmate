package controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import services.CustomerService;

import domain.Customer;


@Controller
@RequestMapping("/register/customer")
public class CustomerController extends AbstractController 
{
	
	@Autowired
	CustomerService customerService;

	@RequestMapping("/register")
	public ModelAndView register() {

	ModelAndView result;

	Customer customer = customerService.create();
	
	result = new ModelAndView("register/registerCustomer");
	result.addObject("customer", customer);

return result;
}

@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
public ModelAndView save(@Valid Customer customer, BindingResult binding) {

	ModelAndView result = new ModelAndView();

	if (binding.hasErrors()) {
		result = createEditModelAndView(customer);
		} else {
			customerService.save(customer);
			result = new ModelAndView("redirect:/security/login.do");			
		}		
return result;
}

@RequestMapping("/registerFailure")
public ModelAndView failure() {
	ModelAndView result;

	result = new ModelAndView("redirect:register.do?showError=true");

return result;
}

protected ModelAndView createEditModelAndView(Customer customer) 
{
	ModelAndView result;
	result = createEditModelAndView(customer, null);
return result;
}

protected ModelAndView createEditModelAndView(Customer customer, String message) 
{
	ModelAndView result;

	result = new ModelAndView("register/registerCustomer");
	result.addObject("customer", customer);
	result.addObject("message", message);
return result;
}

}