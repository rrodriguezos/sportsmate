package controllers;



import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.User;

import services.UserService;

@Controller
@RequestMapping("/register/user")
public class UserController extends AbstractController  
{
// Supporting services ----------------------------------------------------

		@Autowired
		UserService userService;


// Register ----------------------------------------------------------

		@RequestMapping("/register")
		public ModelAndView register() 
		{

			ModelAndView result;

			User user = userService.create();

			result = new ModelAndView("register/registerUser");
			result.addObject("user", user);

			return result;
		}

		// Save ---------------------------------------------------------------

		@SuppressWarnings("deprecation")
		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid User user, BindingResult binding) 
		{

			ModelAndView result = new ModelAndView();

			if (binding.hasErrors()) {
				result = createEditModelAndView(user);
			} else {
				
					userService.save(user);
					result = new ModelAndView("redirect:/security/login.do");					
			}
			
			
			return result;
		}
			
		

		// RegisterFailure -------------------------------------------------------

		@RequestMapping("/registerFailure")
		public ModelAndView failure() 
		{
			ModelAndView result;

			result = new ModelAndView("redirect:register.do?showError=true");

			return result;
		}

		// Ancillary methods ------------------------------------------------------

		protected ModelAndView createEditModelAndView(User user) 
		{
			ModelAndView result;
			result = createEditModelAndView(user, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(User user, String message) 
		{

			ModelAndView result;

			result = new ModelAndView("register/registerUser");
			result.addObject("user", user);
			result.addObject("message", message);
			return result;
		}

	}
