package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import services.EventService;
import services.FriendshipService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;


import domain.Event;
import domain.Friendship;
import domain.Team;
import domain.Tournament;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/profile/user")
public class ProfileUserController extends AbstractController {
	
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
	
	
// List------------------------------------------------------------------------

@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list() 
{

	ModelAndView result;

	User profile = userService.findByPrincipal();
	profile = userService.findOne(profile.getId());

	Collection<Event> events = eventService.findAllEventsByUserId();
	Collection<Tournament> tournaments = tournamentService.
					findAllTournamentsByUserId();
	Collection<Friendship> friendships = friendshipService.
			findAllFriendshipsByUserId();
	Collection<Team> teams = teamService.
			findAllTeamsByUserId();
	

		result = new ModelAndView("profile/list");
		result.addObject("profile", profile);
		result.addObject("actor", "/user");
		result.addObject("requestURI", "profile/user/list.do");
		result.addObject("events", events);
		result.addObject("tournaments", tournaments);
		result.addObject("friendship", friendships);
		result.addObject("team", teams);

return result;

}
		
//Edition----------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(@RequestParam int userId)
{
	
	User profile = userService.findByPrincipal();
	profile = userService.findOne(profile.getId());
	
	if (!profile.equals(userService.findOne(userId))) {
		
		throw new IllegalArgumentException("Not Principal");
	}
	ModelAndView result;
	User user;
	UserForm userForm;
	
	user = userService.findOne(userId);
	userForm= userService.construct(user);
	
	result = createEditModelAndView(userForm);
	
	return result;
}

@RequestMapping( value = "/edit", method = RequestMethod.POST, params= "save")
public ModelAndView save(@Valid UserForm userForm, BindingResult binding)
{
	ModelAndView result;
	User user;
	
	if(binding.hasErrors()){
		result = createEditModelAndView(userForm);
	}else{
		try {
			user = userService.reconstruct(userForm);
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
	String name;
	String surname;
	String email;
	int phone;
	
	
	name = userForm.getName();
	surname = userForm.getSurname();
	email = userForm.getEmail();
	phone = userForm.getPhone();
	
	
	result = new ModelAndView("user/edit");
	result.addObject("userForm", userForm );
	result.addObject("name",name );
	result.addObject("surname",surname );
	result.addObject("email",email );
	result.addObject("phone",phone );
	result.addObject("message", message);
	
	return result;
}
}


