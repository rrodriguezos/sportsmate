package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestTeamService;
import services.TeamService;
import services.UserService;
import controllers.AbstractController;
import domain.RequestTeam;
import domain.Team;
import domain.User;

@Controller
@RequestMapping("/requestTeam/user")
public class RequestTeamUserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private RequestTeamService requestTeamService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	// Constructors -----------------------------------------------------------
	public RequestTeamUserController() 
	{

		super();

	}
	
	//Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() 
	{

		ModelAndView result;
		Collection<RequestTeam> requestTeams;
		User principal;

		principal = userService.findByPrincipal();
		requestTeams = requestTeamService.findAllRequestTeamUser(principal
				.getId());

		result = new ModelAndView("requestTeam/list");

		result.addObject("requestTeams", requestTeams);
		result.addObject("requestURI", "requestTeam/user/list.do");

		return result;

	}
		
	//Accepting request------------------------------------------------------------------
	@RequestMapping(value = "/acceptRequest", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam int requestTeamId) 
	{

		ModelAndView result;
		RequestTeam requestTeam;

		requestTeam = requestTeamService.findOne(requestTeamId);
		teamService.joinTeam(requestTeam.getTeam(), requestTeam);

		result = new ModelAndView("team/list");

		result.addObject("requestURI", "team/user/list.do");

		return result;

	}
		
	//Sending request------------------------------------------------------------------
	@RequestMapping(value = "/sendRequest", method = RequestMethod.GET)
	public ModelAndView sendRequest(@RequestParam int teamId) 
	{

		ModelAndView result;
		Collection<Team> teams;
		Collection<Team> teamsRequested = new ArrayList<Team>();
		Team team;
		Boolean showSend = true;
		Collection<RequestTeam> requestTeam;
		User principal;
		
		principal = userService.findByPrincipal();
		team = teamService.findOne(teamId);
		requestTeamService.sendRequest(teamId);
		teams = teamService.findAllOtherUser(principal.getId());
		requestTeam = requestTeamService.findAllRequestSendFromUser(principal.getId());
		
		for(RequestTeam r: requestTeam){
			teamsRequested.add(r.getTeam());
		}

		result = new ModelAndView("team/list");
		
		result.addObject("teams", teams);
		result.addObject("teamsRequested", teamsRequested);
		result.addObject("showSend", showSend);
		result.addObject("requestURI", "team/user/listAllTeams.do");

		return result;

	}
	
}
