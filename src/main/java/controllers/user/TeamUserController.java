package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import forms.TeamForm;

@Controller
@RequestMapping("/team/user")
public class TeamUserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestTeamService requestTeamService;

	// Constructors -----------------------------------------------------------
	public TeamUserController() 
	{

		super();

	}
	
	//Listing------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() 
	{

		ModelAndView result;
		Collection<Team> teams;
		User principal;
		Boolean showDisjoin = true;

		teams = teamService.findAllTeamsByUserId();
		principal = userService.findByPrincipal();

		result = new ModelAndView("team/list");

		result.addObject("teams", teams);
		result.addObject("principal", principal);
		result.addObject("showDisjoin", showDisjoin);
		result.addObject("requestURI", "team/user/list.do");

		return result;

	}
	
	@RequestMapping(value = "/listAllTeams", method = RequestMethod.GET)
	public ModelAndView listAllTeams()
	{
		
		ModelAndView result;
		Collection<Team> teams;
		Collection<Team> teamsRequested = new ArrayList<Team>();
		User principal;
		Boolean showSend = true;
		Collection<RequestTeam> requestTeam;
		
		principal = userService.findByPrincipal();
		teams = teamService.findAllOtherUser(principal.getId());
		requestTeam = requestTeamService.findAllRequestSendFromUser(principal.getId());
		
		for(RequestTeam r: requestTeam){
			teamsRequested.add(r.getTeam());
		}
		
		result = new ModelAndView("team/list");
		
		result.addObject("teams", teams);
		result.addObject("teamsRequested", teamsRequested);
		result.addObject("principal", principal);
		result.addObject("requestTeam", requestTeam);
		result.addObject("showSend", showSend);
		result.addObject("requestURI", "team/user/listAllTeams.do");
		
		return result;
		
	}
	
	// Display-----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int teamId) 
	{

		ModelAndView result;
		Team team;
		TeamForm teamForm;
		Collection<User> users;
		User principal;

		team = teamService.findOne(teamId);
		teamForm = teamService.construct(team);
		users = team.getUsers();
		principal = userService.findByPrincipal();

		result = new ModelAndView("team/display");

		result.addObject("teamForm", teamForm);
		result.addObject("users", users);
		result.addObject("principal", principal);
		result.addObject("team", team);

		return result;

	}
	
	// Creation---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() 
	{

		ModelAndView result;
		Team team;
		TeamForm teamForm;

		team = teamService.create();
		teamForm = teamService.construct(team);

		result = createEditModelAndView(teamForm);
		result.addObject("teamForm", teamForm);
		result.addObject("requestURI", "team/user/edit.do");

		return result;

	}
	
	// Edition---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int teamId) 
	{
		
		ModelAndView result;
		Team team;
		TeamForm teamForm;

		team = teamService.findOne(teamId);

		teamForm = teamService.construct(team);

		result = createEditModelAndView(teamForm);

		return result;
		
	}
	
	// Save-----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEU")
	public ModelAndView save(@Valid TeamForm teamForm, BindingResult binding) 
	{

		ModelAndView result;
		Team team;

		if (binding.hasErrors()) {
			result = createEditModelAndView(teamForm);
		} else {
			try {

				team = teamService.reconstruct(teamForm);

				teamService.save(team);

				result = new ModelAndView("redirect:list.do");

			} catch (Throwable oops) {

				result = createEditModelAndView(teamForm, "team.commit.error");
			}
		}

		return result;

	}
	
	
	// Delete-------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid TeamForm teamForm,
			BindingResult binding) 
	{
		ModelAndView result;
		Team team;
		
		try {
			team = teamService.reconstruct(teamForm);
			teamService.delete(team);
			result = new ModelAndView("team/list");
		} catch (Throwable oops) {
			result = createEditModelAndView(teamForm,
					"team.commit.error");
		}
		
		return result;
	}
	
	
	// DisJoin a Team------------------------------------------------------------------
	@RequestMapping(value = "/disjoinTeam", method = RequestMethod.GET)
	public ModelAndView DisjoinTeam(@RequestParam int teamId) {

		ModelAndView result;
		Team team;
		Collection<Team> teams;

		team = teamService.findOne(teamId);
		teamService.DisjoinTeam(team);
		teams = teamService.findAllTeamsByUserId();

		result = new ModelAndView("team/list");

		result.addObject("teams", teams);
		result.addObject("requestURI", "team/user/list.do");

		return result;

	}
	
	// Ancillary methods---------------------------------------------------

	protected ModelAndView createEditModelAndView(TeamForm teamForm) 
	{

		ModelAndView result;

		result = createEditModelAndView(teamForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(TeamForm teamForm,
			String message) 
	{

		ModelAndView result;

		result = new ModelAndView("team/edit");

		result.addObject("teamForm", teamForm);
		result.addObject("message", message);

		return result;
	}	
	
}
