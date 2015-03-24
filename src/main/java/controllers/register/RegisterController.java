package controllers.register;



import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;







import services.CustomerService;
import services.UserService;
import controllers.AbstractController;
import domain.Customer;
import domain.User;
import forms.CustomerForm;
import forms.UserForm;


@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController
{
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
public ModelAndView register() 
{
	ModelAndView result;
	CustomerForm customerForm = new CustomerForm();
	result = new ModelAndView("register/registerCustomer");
	result.addObject("actor", "customer");
	result.addObject("customerForm", customerForm);
	return result;
}
	
@SuppressWarnings("deprecation")
@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST, params = "save")
public ModelAndView save(@Valid CustomerForm customerForm,
			BindingResult binding) {
	ModelAndView result;
	Boolean contrase�a;
	
	contrase�a = customerForm.getPassword2().equals(
				customerForm.getPassword());

	int mescc = customerForm.getCreditCard().getExpirationMonth();
	int a�occ = customerForm.getCreditCard().getExpirationYear();
	int mes = new Date(System.currentTimeMillis()).getMonth() + 1;
	int a�o = new Date(System.currentTimeMillis()).getYear() + 1900;

		if (binding.hasErrors() || !contrase�a) {
			System.out.println(binding.toString());

			result = new ModelAndView("register/registerCustomer");
			result.addObject("customerForm", customerForm);
			result.addObject("actor", "customer");
			if (mescc < mes) {
				if (!(a�occ > a�o)) {
					result.addObject("message",
							"register.commit.error.creditCard");
				}
			} else if (mescc == mes) {
				if (!(a�occ >= a�o)) {
					result.addObject("message",
							"register.commit.error.creditCard");
				}

			} else if (mescc > mes) {
				if (!(a�occ >= a�o)) {
					result.addObject("message",
							"register.commit.error.creditCard");
				}

			}
			if (!contrase�a) {
				result.addObject("message", "register.commit.password");
			}

		} else {
			try {
				Assert.notNull(customerForm);
				Customer customer = customerService.reconstruct(customerForm);
				customerService.save(customer);

				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (Throwable oops) {

				result = new ModelAndView("register/registerCustomer");
				result.addObject("customerForm", customerForm);
				result.addObject("actor", "customer");


				if (mescc < mes) {
					if (!(a�occ > a�o)) {
						result.addObject("message",
								"register.commit.error.creditCard");
					}
				} else if (mescc == mes) {
					if (!(a�occ >= a�o)) {
						result.addObject("message",
								"register.commit.error.creditCard");
					}

				} else if (mescc > mes) {
					if (!(a�occ >= a�o)) {
						result.addObject("message",
								"register.commit.error.creditCard");
					}

				}
				System.out.println(customerService.userRegistered(customerForm
						.getUsername())
						|| userService.userRegistered(customerForm
								.getUsername()));
				if (customerService.userRegistered(customerForm.getUsername())
						|| userService.userRegistered(customerForm
								.getUsername())) {
					result.addObject("message",
							"register.commit.duplicatedUsername");
}
				

}
}
		return result;
}

//User

@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
public ModelAndView registerUser() 
{
		ModelAndView result;
		UserForm userForm = new UserForm();
		result = new ModelAndView("register/registerUser");
		result.addObject("actor", "user");
		result.addObject("userForm", userForm);

		return result;
}

@RequestMapping(value = "/registerUser", method = RequestMethod.POST, params = "save")
public ModelAndView saveUser(@Valid UserForm userForm,
			BindingResult binding) {
	ModelAndView result;
	Boolean contrase�a;

	contrase�a = userForm.getPassword2().equals(userForm.getPassword());

		if (binding.hasErrors() || !contrase�a) {

			result = new ModelAndView("register/registerUser");
			result.addObject("userForm", userForm);
			result.addObject("actor", "user");


			if (!contrase�a) {
				result.addObject("message", "register.commit.password");
			}

		} else {
			try {
				Assert.notNull(userForm);
				User user = userService
						.reconstruct(userForm);
				userService.save(user);
				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (Throwable oops) {

				result = new ModelAndView("register/registerUser");
				result.addObject("userForm", userForm);
				result.addObject("actor", "user");


				if (customerService.userRegistered(userForm.getUsername())
						|| userService.userRegistered(userForm
								.getUsername())) {
					result.addObject("message",
							"register.commit.duplicatedUsername");

}
				
}
}
		return result;
}
}

	
	
	
	
	
	
	
	
	

