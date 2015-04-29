package controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MatchService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import domain.Team;
import domain.Tournament;
import domain.User;

@Controller
@RequestMapping("/tournament")
public class TournamentsController extends AbstractController 
{
//Services----------------------------------------------------------	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private UserService userService;
	

	//List--------------------------------------------------------------
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Tournament> tournaments;
		Boolean showJoin = true;
		User principal;

		tournaments = tournamentService.findAll();
		principal = userService.findByPrincipal();

		result = new ModelAndView("tournament/listAll");
		result.addObject("tournaments", tournaments);
		result.addObject("showJoin", showJoin);
		result.addObject("userTournaments", principal.getTournaments());
		result.addObject("requestURI", "tournament/listAll.do");

		return result;
	}
	
	// Join a
		// Tournament------------------------------------------------------------------
		@RequestMapping(value = "/joinTournament", method = RequestMethod.GET)
		public ModelAndView joinTournament(@RequestParam int tournamentId,@RequestParam int teamId) 
		{

			ModelAndView result;
			Tournament tournament;
			Team team;
			Collection<Tournament> tournaments;

			tournament = tournamentService.findOne(tournamentId);
			team = teamService.findOne(teamId);
			tournamentService.joinTournament(tournament,team);
			tournaments = tournamentService.findAll();

			result = new ModelAndView("tournament/list");

			result.addObject("tournaments", tournaments);
			result.addObject("requestURI", "tournament/user/list.do");

			return result;

		}

		// DisJoin a
		// Tournament------------------------------------------------------------------
		@RequestMapping(value = "/disjoinATeamToTournament", method = RequestMethod.GET)
		public ModelAndView DisjoinTournament(@RequestParam int tournamentId) 
		{

			ModelAndView result;
			Tournament tournament;
			Collection<Tournament> tournaments;

			tournament = tournamentService.findOne(tournamentId);
			tournamentService.DisjoinTournament(tournament);
			tournaments = tournamentService.findAll();

			result = new ModelAndView("tournament/list");

			result.addObject("tournaments", tournaments);
			result.addObject("requestURI", "tournament/user/list.do");

			return result;

		}
	
	
}




