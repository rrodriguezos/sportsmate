package controllers.register;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.User;
import forms.UserForm;
import services.UserService;

@Controller
@RequestMapping("/register/user")
public class UserControllerRegister extends AbstractController  
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		UserForm userForm = new UserForm();
		result = new ModelAndView("register/user");
		result.addObject("actor", "user");
		result.addObject("userForm", userForm);
		return result;
	}
	
	
@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
public ModelAndView save(@Valid UserForm userForm,	BindingResult binding) 
{
	ModelAndView result;
	
		result = new ModelAndView("register/user");
		result.addObject("userForm", userForm);
		result.addObject("actor", "user");		

		User user = userService.reconstruct(userForm);
		userService.save(user);

		result = new ModelAndView("redirect:../welcome/index.do");
	
		return result;
}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//
//	@Autowired
//	UserService userService;
//
//	@RequestMapping("/register")
//	public ModelAndView register() 
//{
//
//	ModelAndView result;
//
//	User user = userService.create();
//	UserForm userForm=new UserForm();
//
//	result = new ModelAndView("register/registerUser");
//	result.addObject("user", userForm);
//
//return result;
//}
//	
//@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
//public ModelAndView save(@Valid User user, BindingResult binding) 
//{
//
//	ModelAndView result = new ModelAndView();
//	if (binding.hasErrors()) {
//		result = createEditModelAndView(user);
//		} else {		
//		userService.save(user);
//		result = new ModelAndView("redirect:/security/login.do");					
//}
//return result;
//}
//	@RequestMapping("/registerFailure")
//	public ModelAndView failure() 
//{
//	ModelAndView result;
//
//	result = new ModelAndView("redirect:register.do?showError=true");
//
//return result;
//}
//	protected ModelAndView createEditModelAndView(User user) 
//{
//	ModelAndView result;
//	result = createEditModelAndView(user, null);
//return result;
//}
//	protected ModelAndView createEditModelAndView(User user, String message) 
//{
//
//	ModelAndView result;
//	result = new ModelAndView("register/registerUser");
//	result.addObject("user", user);
//	result.addObject("message", message);
//return result;
//}
//
//}
