package controllers.register;

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
public class RegisterController extends AbstractController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		CustomerForm customerForm = new CustomerForm();
		result = new ModelAndView("register/registerCustomer");
		result.addObject("actor", "customer");
		result.addObject("customerForm", customerForm);
		return result;
	}

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CustomerForm customerForm,
			BindingResult binding) {
		ModelAndView result;
		Boolean contraseña;

		contraseña = customerForm.getPassword2().equals(
				customerForm.getPassword());

		if (binding.hasErrors() || !contraseña
				|| customerForm.getUsername().length() <= 3) {
			System.out.println(binding.toString());

			result = new ModelAndView("register/registerCustomer");
			result.addObject("customerForm", customerForm);
			result.addObject("actor", "customer");
			if (customerForm.getUsername().length() <= 3) {
				result.addObject("message", "register.commit.tam");
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

				System.out.println(customerService.userRegistered(customerForm
						.getUsername())
						|| userService.userRegistered(customerForm
								.getUsername()));
				if (customerForm.getUsername().length() <= 3) {
					result.addObject("message", "register.commit.tam");
				} else if (customerService.userRegistered(customerForm
						.getUsername())
						|| userService.userRegistered(customerForm
								.getUsername())) {
					result.addObject("message",
							"register.commit.duplicatedUsername");
				}

			}
		}
		return result;
	}

	// User

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView registerUser() {
		ModelAndView result;
		UserForm userForm = new UserForm();
		result = new ModelAndView("register/registerUser");
		result.addObject("actor", "user");
		result.addObject("userForm", userForm);

		return result;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, params = "save")
	public ModelAndView saveUser(@Valid UserForm userForm, BindingResult binding) {
		ModelAndView result;
		Boolean contraseña;

		contraseña = userForm.getPassword2().equals(userForm.getPassword());

		if (binding.hasErrors() || !contraseña
				|| userForm.getUsername().length() <= 3) {

			result = new ModelAndView("register/registerUser");
			result.addObject("userForm", userForm);
			result.addObject("actor", "user");

			if (!contraseña) {
				result.addObject("message", "register.commit.password");
			} else if (userForm.getUsername().length() <= 3) {
				result.addObject("message", "register.commit.tam");
			}

		} else {
			try {
				Assert.notNull(userForm);
				User user = userService.reconstruct(userForm);
				userService.save(user);
				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (Throwable oops) {
				System.out.println("exceptcion");
				System.out.println(oops.toString());
				result = new ModelAndView("register/registerUser");
				result.addObject("userForm", userForm);
				result.addObject("actor", "user");
				if (userForm.getUsername().length() <= 3) {
					result.addObject("message", "register.commit.tam");
				}
				if (customerService.userRegistered(userForm.getUsername())
						|| userService.userRegistered(userForm.getUsername())) {
					result.addObject("message",
							"register.commit.duplicatedUsername");

				}

			}
		}
		return result;
	}

}
