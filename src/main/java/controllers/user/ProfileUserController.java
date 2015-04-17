package controllers.user;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;





import services.EventService;
import services.FriendshipService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;


import domain.Actor;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/profile/user")
public class ProfileUserController extends AbstractController {
// Services -------------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private TeamService teamService;
	
	


//Display----------------------------------------------------------------------

@RequestMapping(value = "/display", method = RequestMethod.GET)
public ModelAndView display() {

	ModelAndView result;
	UserForm userForm;
	Actor actor;
	
	
	actor = userService.findByPrincipal();
	User profile = userService.findByPrincipal();
	profile = userService.findOne(profile.getId());

	userForm = userService.construct(profile);
	
	
	result = new ModelAndView("profile/display");		

	result.addObject("userForm", userForm);
	Double rating = profile.getRating();
	result.addObject("rating", rating);
	result.addObject("requestURI", "profile/user/display.do");
	result.addObject("actor",actor);
	

	return result;

}
		
//Edition----------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.GET)
public ModelAndView edit()
{
	ModelAndView result;
	User user;
	UserForm userForm;
	
	user = userService.findByPrincipal();
	userForm = userService.construct(user);
	
	result = createEditModelAndView(userForm);
	
	return result;
}
//Save --------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.POST, params= "save")
public ModelAndView save(@Valid UserForm userForm, BindingResult binding)
{
	ModelAndView result;
	User user;

	if (binding.hasErrors()) {
		result = createEditModelAndView(userForm);
	} else {
		try {

			user = userService.reconstructEdit(userForm);

			userService.save(user);

			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {

			result = createEditModelAndView(userForm, "user.commit.error");
		}
	}
	return result;
}

//Delete-----------------------------------------------------------------------

@RequestMapping( value ="/edit", method = RequestMethod.POST , params = "delete")
public ModelAndView delete(UserForm userForm, BindingResult binding)
{
	ModelAndView result;
	User user;
	
	try {
		user = userService.reconstruct(userForm);
		userService.delete(user);
		result = new ModelAndView("redirect:list.do");
	} catch (Throwable oops) {
		result = createEditModelAndView(userForm, "user.commit.error");
	}
	return result;


}

//The ancillary methods--------------------------------------------------------

protected ModelAndView createEditModelAndView(UserForm userForm){
	ModelAndView result;
	
	result = createEditModelAndView(userForm, null);
	
	return result;
}

protected ModelAndView createEditModelAndView(UserForm userForm, String message)
{
	ModelAndView result;
	
	result = new ModelAndView("user/edit");
	
	result.addObject("userForm", userForm);;
	result.addObject("message", message);
	
	return result;
}
}


