package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class RegisterUserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public RegisterUserController() {
		super();
	}

	// Display-----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView result;
		User user;
		UserForm userForm;

		user = userService.findByPrincipal();
		userForm = userService.construct(user);

		result = new ModelAndView("user/display");

		result.addObject("userImagen", user.isErrorImagen());
		result.addObject("userForm", userForm);
		result.addObject("user", user);
		result.addObject("rating", user.getRating());

		return result;

	}

	// Create------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		UserForm userForm;

		userForm = userService.construct();
		result = createEditModelAndView(userForm);

		return result;
	}

	// Edition---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int userId) {
		ModelAndView result;
		User user;
		UserForm userForm;

		user = userService.findOneUserToEdit(userId);
		userForm = userService.construct(user);

		userForm.setUsername(user.getUserAccount().getUsername());
		userForm.setPassword(user.getUserAccount().getPassword());
		userForm.setPassword2(user.getUserAccount().getPassword());
		userForm.setTerms(true);

		result = createEditModelAndView(userForm);

		return result;
	}

	// Save-----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserForm userForm,
			BindingResult bindingResult) {
		ModelAndView result;
		User user;

//		if (bindingResult.hasErrors() || userForm.getUsername().length() <= 3) {
//
//			result = createEditModelAndView(userForm);
//			if (userForm.getUsername().length() <= 3) {
//				result.addObject("message", "register.commit.penemuychico");
//			}
		if (bindingResult.hasErrors()) {

			result = createEditModelAndView(userForm);
//			if (userForm.getUsername().length() <= 3) {
//				result.addObject("message", "register.commit.penemuychico");
//			}
		} else {
			try {

				user = userService.reconstruct(userForm);

				userService.save(user);

				result = new ModelAndView("redirect:../welcome/index.do");

			} catch (Throwable oops) {
				if (oops instanceof DataIntegrityViolationException) {

					result = createEditModelAndView(userForm,
							"user.duplicated.username");
				} 
//				else if (userForm.getUsername().length() <= 3) {
//					result = createEditModelAndView(userForm,
//							"register.commit.penemuychico");
//			}	
				 else {
					result = createEditModelAndView(userForm,
							"user.commit.error");
				}
			}
		}

		return result;
	}

	// Ancillary methods ----------------------------------------------

	protected ModelAndView createEditModelAndView(UserForm userForm) {
		ModelAndView result;

		result = createEditModelAndView(userForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(UserForm userForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("user/edit");

		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, 
							  ServletRequestDataBinder binder) throws ServletException 
	{
		
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	
	}
	
}
