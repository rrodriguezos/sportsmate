package controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import domain.Tournament;
import services.MatchService;
import services.TeamService;
import services.TournamentService;

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
	

//List--------------------------------------------------------------
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Tournament> tournaments;

		tournaments = tournamentService.findAll();

		result = new ModelAndView("tournament/listAll");
		result.addObject("tournaments", tournaments);
		result.addObject("requestURI", "tournament/listAll.do");

		return result;
	}
	
	
}




