package controllers.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MatchService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;
import domain.Match;
import domain.Team;
import domain.Tournament;
import domain.User;
import forms.TournamentForm;

@Controller
@RequestMapping("/tournament/user")
public class TournamentUserController extends AbstractController {
	// Services---------------------------------------------------------------------
	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private MatchService matchService;

	// Listing----------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Tournament> tournaments;
		Boolean showDisjoin = true;
		User principal;

		tournaments = tournamentService.findAllTournamentByUser();
		principal = userService.findByPrincipal();
		
		result = new ModelAndView("tournament/list");

		result.addObject("tournaments", tournaments);
		result.addObject("showDisjoin", showDisjoin);
		result.addObject("principal", principal);
		result.addObject("actualDate", new Date());
		result.addObject("requestURI", "tournament/user/list.do");

		return result;

	}

	// Display-----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tournamentId) {

		ModelAndView result;
		Tournament tournament;
		Collection<Team> teams;
		Collection<Match> matches;

		tournament = tournamentService.findOneToJoin(tournamentId);
		teams = teamService.findAllTeamsByTournamentId(tournamentId);
		matches = matchService.findAllMatchesByTournament(tournament);

		result = new ModelAndView("tournament/display");
		result.addObject("miId", userService.findByPrincipal().getId());
		result.addObject("tournament", tournament);
		result.addObject("tournamentId", tournamentId);
		result.addObject("matches", matches);
		result.addObject("teams", teams);

		Boolean puedeEditar = true;
		Date now = new Date(System.currentTimeMillis());
		if (tournament.getStartMoment().before(now)) {
			puedeEditar = false;
		}
		result.addObject("puedeEditar", puedeEditar);

		return result;

	}

	// Create----------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Tournament tournament;
		TournamentForm tournamentForm;

		tournament = tournamentService.create();
		tournamentForm = tournamentService.construct(tournament);
		result = createEditModelAndView(tournamentForm);
		result.addObject("tournamentForm", tournamentForm);
		Integer torneosActicvos = userService.getAllTournamentsActive().size();
		result.addObject("activos", torneosActicvos);
		result.addObject("requestURI", "tournament/user/edit.do");

		return result;
	}

	// Edition---------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int tournamentId) {
		ModelAndView result;
		Tournament tournament;
		Collection<String> places;
		Collection<Match> matches;
		Collection<Team> teams;
		TournamentForm tournamentForm;

		matches = matchService.findAll();
		teams = teamService.findAll();

		places = tournamentService.places();
		tournament = tournamentService.findOneToEdit(tournamentId);

		tournamentForm = tournamentService.construct(tournament);
		if (!places.contains(tournament.getPlace())) {

			tournamentForm.setOtherSportCenter(tournament.getPlace());
		}

		result = createEditModelAndView(tournamentForm);
		result.addObject("matches", matches);
		result.addObject("teams", teams);

		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveTU")
	public ModelAndView save(@Valid TournamentForm tournamentForm,
			BindingResult binding) {
		ModelAndView result;
		System.out.println(tournamentForm.getPlace());
		Tournament tournament;
		Date now = new Date(System.currentTimeMillis());
		Boolean croqueta = !tournamentForm.getPlace().equals("Other")
				&& tournamentForm.getOtherSportCenter().length() > 0;
		if (binding.hasErrors() || croqueta) {
			System.out.println("Binding " + binding.toString());
			if (croqueta) {
				result = createEditModelAndView(tournamentForm,
						"tournament.choose.one");
			} else {
				result = createEditModelAndView(tournamentForm);
			}
		} else {
			try {
				tournament = tournamentService.reconstruct(tournamentForm);

				tournamentService.save(tournament);

				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				System.out.println("oops " + oops.getLocalizedMessage());
				System.out.println(tournamentForm.getPlace() + ","
						+ tournamentForm.getOtherSportCenter().length());
				String message = "tournament.commit.error";

				if (tournamentForm.getStartMoment().before(now)
						|| tournamentForm.getFinishMoment().before(now)) {
					message = "tournament.commit.error.fechas";
				} else if (!tournamentForm.getPlace().equals("Other")
						&& tournamentForm.getOtherSportCenter().length() > 0
						|| tournamentForm.getPlace().equals("Other")
						&& tournamentForm.getOtherSportCenter().length() == 0) {
					message = "tournament.choose";
				}
				result = createEditModelAndView(tournamentForm, message);
			}
		}
		return result;

	}

	// Delete-----------------------------------------------------------------------
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "deleteTU")
	public ModelAndView delete(TournamentForm tournamentForm,
			BindingResult binding) {
		ModelAndView result;
		try {

			tournamentService.delete(tournamentForm);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			Date currentDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dateFormat.format(currentDate);
			System.out.println(oops.getLocalizedMessage());
			result = new ModelAndView();
			result.addObject("tournamentForm", tournamentForm);
			if (currentDate.compareTo(tournamentForm.getFinishMoment()) > 0) {
				result.addObject("message",
						"tournament.commit.error.not.finish");
			} else {
				result.addObject("message", "tournament.commit.error");
			}
		}
		return result;
	}

	// Choose a team--------------------------------------------------------
	@RequestMapping(value = "/chooseATeam", method = RequestMethod.GET)
	public ModelAndView chooseATeam(@RequestParam int tournamentId) {

		ModelAndView result;
		Collection<Team> teams;
		User principal;
		TournamentForm tournamentForm;
		Tournament tournament;
		Collection<Team> empty;
		String requestURI;

		principal = userService.findByPrincipal();
		empty = new ArrayList<Team>();
		teams = teamService.findAllTeamsUserCaptain(principal.getId());
		tournament = tournamentService.findOneToJoin(tournamentId);
		tournamentForm = tournamentService.construct(tournament);
		tournamentForm.setTeams(empty);
		requestURI = "tournament/user/joinATeamToTournament.do?tournamentId="
				+ tournamentId;

		result = new ModelAndView("tournament/chooseATeam");
		result.addObject("teams", teams);
		result.addObject("tournamentForm", tournamentForm);
		result.addObject("principal", principal);
		result.addObject("requestURI", requestURI);

		return result;

	}

	// Join a Tournament--------------------------------------------------------
	@RequestMapping(value = "/joinATeamToTournament", method = RequestMethod.POST, params = "join")
	public ModelAndView joinATeamToTournament(TournamentForm tournamentForm,
			BindingResult binding, @RequestParam int tournamentId) {

		ModelAndView result;
		Team team = null;
		Tournament tournament;
		try {
			for (Team t : tournamentForm.getTeams()) {
				team = t;
			}

			tournament = tournamentService.findOneToJoin(tournamentId);

			tournamentService.joinTournament(tournament, team);

			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView("tournament.commit.error.Join",
					tournamentId);
		}
		// }
		return result;

	}

	// DisJoin
	// aTournament-------------------------------------------------------
	@RequestMapping(value = "/disjoinATeamToTournament", method = RequestMethod.GET)
	public ModelAndView disjoinATeamToTournament(@RequestParam int tournamentId) {

		ModelAndView result;
		Tournament tournament;
		Collection<Tournament> tournaments;

		tournament = tournamentService.findOneToJoin(tournamentId);
		tournamentService.DisjoinTournament(tournament);
		tournaments = tournamentService.findAllTournamentByUser();

		result = new ModelAndView("tournament/list");

		result.addObject("tournaments", tournaments);
		result.addObject("requestURI", "tournament/user/list.do");

		return result;

	}

	// Ancillary
	// methods------------------------------------------------------------

	protected ModelAndView createEditModelAndView(TournamentForm tournamentForm) {

		ModelAndView result;

		result = createEditModelAndView(tournamentForm, null);
		Integer torneosActicvos = userService.getAllTournamentsActive().size();
		result.addObject("activos", torneosActicvos);
		return result;

	}

	protected ModelAndView createEditModelAndView(
			TournamentForm tournamentForm, String message) {

		ModelAndView result;
		Collection<String> sports;
		Collection<String> places;
		Collection<Match> matches;
		Collection<Team> teams;

		sports = tournamentService.sports();
		places = tournamentService.places();
		matches = matchService.findAll();
		teams = teamService.findAll();

		result = new ModelAndView("tournament/edit");

		result.addObject("tournamentForm", tournamentForm);
		result.addObject("sports", sports);
		result.addObject("places", places);
		result.addObject("matches", matches);
		result.addObject("teams", teams);
		result.addObject("message", message);
		Integer torneosActicvos = userService.getAllTournamentsActive().size();
		result.addObject("activos", torneosActicvos);

		return result;
	}

	public ModelAndView createEditModelAndView(String message, int tournamentId) {
		ModelAndView result;
		Collection<Team> teams;
		User principal;
		TournamentForm tournamentForm;
		Tournament tournament;
		Collection<Team> empty;
		String requestURI;

		principal = userService.findByPrincipal();
		empty = new ArrayList<Team>();
		teams = teamService.findAllTeamsUserCaptain(principal.getId());
		tournament = tournamentService.findOneToJoin(tournamentId);
		tournamentForm = tournamentService.construct(tournament);
		tournamentForm.setTeams(empty);
		requestURI = "tournament/user/joinATeamToTournament.do?tournamentId="
				+ tournamentId;

		result = new ModelAndView("tournament/chooseATeam");
		result.addObject("teams", teams);
		result.addObject("message", message);
		result.addObject("tournamentForm", tournamentForm);
		result.addObject("principal", principal);
		result.addObject("requestURI", requestURI);
		Integer torneosActicvos = userService.getAllTournamentsActive().size();
		result.addObject("activos", torneosActicvos);
		return result;
	}

}
