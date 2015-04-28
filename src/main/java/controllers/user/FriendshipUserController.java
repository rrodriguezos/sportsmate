package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FriendshipService;
import services.TeamService;
import services.UserService;
import controllers.AbstractController;
import domain.Friendship;
import domain.Friendship;
import domain.Team;
import domain.User;

@Controller
@RequestMapping("/friendship/user")
public class FriendshipUserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private FriendshipService friendshipService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------
	public FriendshipUserController() 
	{

		super();

	}
	
	//Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() 
	{

		ModelAndView result;
		Collection<Friendship> friendships;
		User principal;

		principal = userService.findByPrincipal();
		friendships = friendshipService.findAllRequestFriendship(principal.getId());

		result = new ModelAndView("friendship/list");

		result.addObject("friendships", friendships);
		result.addObject("principal",principal);
		result.addObject("requestURI", "friendship/user/list.do");

		return result;

	}
	
	//Accepting request------------------------------------------------------------------
	@RequestMapping(value = "/acceptRequest", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam int friendshipId) {

		ModelAndView result;
		Friendship friendship;

		friendship = friendshipService.findOne(friendshipId);
		friendshipService.acceptRequest(friendship.getUserFriend(), friendship);

		result = new ModelAndView("friendship/list");

		result.addObject("requestURI", "friendship/user/list.do");

		return result;

	}
	
	//Sending request------------------------------------------------------------------
	@RequestMapping(value = "/sendRequest", method = RequestMethod.GET)
	public ModelAndView sendRequest(@RequestParam int userFriendId) 
	{

		ModelAndView result;
		Collection<Friendship> friendships;
		
		friendshipService.sendRequest(userFriendId);
		friendships = friendshipService.findAllFriendshipsByUserId();
		
		result = new ModelAndView("redirect:../../welcome/index.do");

		result.addObject("friendships", friendships);

		return result;

	}
}
