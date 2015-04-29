package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MatchService;
import services.TeamService;
import services.TournamentService;
import domain.Tournament;

@Controller
@RequestMapping("/tournament")
public class TournamentsController extends AbstractController {
	// Services----------------------------------------------------------
	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private MatchService matchService;

	// List--------------------------------------------------------------
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Tournament> all;
		Date now = new Date(System.currentTimeMillis());
		all = tournamentService.findAll();
		Collection<Tournament> tournaments = new HashSet<Tournament>();
		for (Tournament a : all) {
			if (a.getFinishMoment().before(now)) {
				tournaments.add(a);
			}
		}
		result = new ModelAndView("tournament/listAll");
		result.addObject("tournaments", tournaments);
		result.addObject("requestURI", "tournament/listAll.do");

		return result;
	}

}
