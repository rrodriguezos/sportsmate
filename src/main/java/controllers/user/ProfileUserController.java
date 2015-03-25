package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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

@Controller
@RequestMapping("/profile/user/")
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

@RequestMapping(value = "/profile", method = RequestMethod.GET)
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
		result.addObject("requestURI", "profile/user/profile.do");
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
	
	user = userService.findOne(userId);
	Assert.notNull(user);
	result = createEditModelAndView(user);
	
	return result;
}

@RequestMapping( value = "/edit", method = RequestMethod.POST, params= "save")
public ModelAndView save(@Valid User user, BindingResult binding)
{
	ModelAndView result;
	
	if(binding.hasErrors()){
		result = createEditModelAndView(user);
	}else{
		try{
			userService.save(user);
			result= new ModelAndView("redirect:profile.do");
		}catch(Throwable oops){
			result = createEditModelAndView(user, "user.commit.error");
		}
}
	return result;
}

@RequestMapping( value ="/edit", method = RequestMethod.POST , params = "delete")
public ModelAndView delete(User user, BindingResult binding)
{
	ModelAndView result;
	
	try{
		userService.delete(user);
		result = new ModelAndView("redirect:login.do");
	}catch(Throwable oops){
		result = createEditModelAndView(user, "user.commit.error");
}
	return result;


}

//The ancillary methods--------------------------------------------------------

protected ModelAndView createEditModelAndView(User user){
	ModelAndView result;
	
	result = createEditModelAndView(user, null);
	
	return result;
}

protected ModelAndView createEditModelAndView(User user, String message)
{
	ModelAndView result;
	String name;
	String surname;
	String email;
	String phone;
	
	
	name = user.getName();
	surname = user.getSurname();
	email = user.getEmail();
	phone = user.getPhone();
	
	
	result = new ModelAndView("user/edit");
	result.addObject("user", user );
	result.addObject("name",name );
	result.addObject("surname",surname );
	result.addObject("email",email );
	result.addObject("phone",phone );
	result.addObject("message", message);
	
	return result;
}
}


