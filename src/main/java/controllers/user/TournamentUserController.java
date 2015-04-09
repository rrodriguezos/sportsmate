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

import services.MatchService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import controllers.AbstractController;
import domain.Match;
import domain.Team;
import domain.Tournament;
import forms.TournamentForm;
@Controller
@RequestMapping("/tournament/user")
public class TournamentUserController extends AbstractController 
{
//Services---------------------------------------------------------------------	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MatchService matchService;
	

//Listing----------------------------------------------------------------------
@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list()
	{
	ModelAndView result;
	Collection<Tournament> tournaments;
	
	tournaments = tournamentService.findAllTournamentsByUserId();
	
	result = new ModelAndView("tournament/list");
	
	result.addObject("tournaments", tournaments);
	result.addObject("requestURI", "tournament/user/list.do");
	
	return result;

}

//Display-----------------------------------------------------------------

@RequestMapping(value = "/display", method = RequestMethod.GET)
public ModelAndView display(@RequestParam int tournamentId)
{

	ModelAndView result;
	Tournament tournament;
	Collection<Team> teams;
	Collection<Match> matches;
	
	tournament = tournamentService.findOne(tournamentId);
	teams = teamService.findAllTeamsByTournamentId(tournamentId);
	matches = matchService.findAllMatchesByTournament(tournament);
	
	result = new ModelAndView("tournament/display");
	
	result.addObject("tournament", tournament);
	result.addObject("matches", matches);
	result.addObject("teams", teams);
	
	return result;

}

// Create----------------------------------------------------------------------

@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView create() 
{
	ModelAndView result;
	Tournament tournament;
	TournamentForm tournamentForm;
	
	tournament = tournamentService.create();
	tournamentForm= tournamentService.construct(tournament);
	
	result = createEditModelAndView(tournamentForm);
	result.addObject("requestURI", "tournament/user/edit.do");
	
	return result;	
}

// Edition---------------------------------------------------------------------

@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(@RequestParam int tournamentId)
{
ModelAndView result;
Tournament tournament;
TournamentForm tournamentForm;	
Collection<String> places;

places = tournamentService.places();
tournament = tournamentService.findOneToEdit(tournamentId);	

tournamentForm = tournamentService.construct(tournament);
if(!places.contains(tournament.getPlace())){
	
	tournamentForm.setOtherSportCenter(tournament.getPlace());
}

result = createEditModelAndView(tournamentForm);

return result;
}

//Save-------------------------------------------------------------------------
@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveTU")
public ModelAndView save(@Valid TournamentForm tournamentForm, BindingResult binding)
{
	ModelAndView result;
	Tournament tournament;
	
	if(binding.hasErrors()){
		result = createEditModelAndView(tournamentForm);
	}else{
		try{
			tournament = tournamentService.reconstruct(tournamentForm);
			
			tournamentService.save(tournament);
			
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			
			result = createEditModelAndView(tournamentForm, "tournament.commit.error");
		}
	}
	return result;

}

//Delete-----------------------------------------------------------------------
@RequestMapping(value = "display", method = RequestMethod.POST, params = "deleteTU")
public ModelAndView delete(@Valid TournamentForm tournamentForm, BindingResult binding) 
{
	ModelAndView result;
	Tournament tournament;
	try{
		tournament = tournamentService.reconstruct(tournamentForm);
		
		tournamentService.delete(tournament);
		
		result = new ModelAndView("redirect:list.do");
	}catch(Throwable oops){
		result = createEditModelAndView(tournamentForm, "tournament.commit.error");
	}
	return result;
}

//Ancillary methods------------------------------------------------------------
	
protected ModelAndView createEditModelAndView(TournamentForm tournamentForm) 
{
	
	ModelAndView result;
	
	result = createEditModelAndView(tournamentForm, null);
	
	return result;
	
}

protected ModelAndView createEditModelAndView(TournamentForm tournamentForm, String message) 
{
	
	ModelAndView result;
	Collection<String> sports;
	Collection<String> places;
	Collection<Match> matches;
	Collection<Team> teams;
	
	sports = tournamentService.sports();
	places = tournamentService.places();
	matches = matchService.findAllMatchesByTournament(tournamentForm);
	teams = teamService.findAllTeamsByTournament(tournamentForm);
	
	result = new ModelAndView("tournament/edit");
		
	result.addObject("tournamentForm", tournamentForm);
	result.addObject("sports", sports);
	result.addObject("places", places);
	result.addObject("matches", matches);
	result.addObject("teams", teams);
	result.addObject("message", message);		

	return result;
}	


}
