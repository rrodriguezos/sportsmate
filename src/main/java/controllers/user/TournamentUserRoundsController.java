package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;		

import security.Authority;
import security.LoginService;
import services.TeamService;
import services.TournamentService;
import services.UserService;
import domain.Match;
import domain.Team;
import domain.Tournament;
import domain.User;



@Controller
@RequestMapping("/tournament/user/rounds")
public class TournamentUserRoundsController 
{
		
		
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private TournamentService tournamentService;
		
		@Autowired 
		private services.MatchService matchService;
		
		@Autowired
		private TeamService teamService;
		
		@Autowired
		private LoginService loginService;
	
	
	
	@RequestMapping("/addTeamTest")
	public ModelAndView addTeamTest(){
		
		Authority authority =new Authority();
		authority.setAuthority("CUSTOMER");
		
		List<Tournament> tournaments = null;
		if (LoginService.getPrincipal().getAuthorities().contains(authority)){
			tournaments= (List<Tournament>) tournamentService.findAllTournamentsCreatedByCustomerId();
		}else{
			tournaments=(List<Tournament>) tournamentService.findAllTournamentsCreatedByUserId();
		}
		
		Tournament tournament=tournaments.get(1);
		
		String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };

		int numRandon = (int) Math.round(Math.random() * 26 ) ;
		
		
		Team team=new Team();
		team.setCaptain(userService.findByPrincipal());
		team.setName(abecedario[numRandon]);
		
		Collection<User> users=new ArrayList<User>();
		users.add(userService.findByPrincipal());
		team.setUsers(users);
		
		Collection<domain.Match> matchs=new ArrayList<domain.Match>();
		
		team.setMatchs(matchs);
		team.setMaxNumber(3);
		
		team.setTournaments(tournaments);
		
		
		
		
		Collection<Team> teams = tournament.getTeams();
		teams.add(team);
		
		tournament.setTeams(teams);
		teamService.save(team);
		tournamentService.save(tournament);
		
		return null;
	}
	
	@RequestMapping("/createFirstRounds")
	public ModelAndView createRounds(@RequestParam int id)
	{
		Tournament tournament=tournamentService.findOne(id);
		
		/* Creamos las rondas segun los teams asociados */
		
		/* regla de negocio, debemos de tener en cuenta si hay un numero impar de equipos */
		
		if((tournament.getTeams().size() % 2) == 0){
			
			Collection<Match> matchs= tournament.getMatches();
			for(int i=0 ; i < tournament.getTeams().size() ; i+=2){
			
				System.out.println(tournament.getTitle());
				
				domain.Match match=new Match();
				match.setCreationMoment(new Date());
				match.setDescription(".");
				match.setFinishMoment(new Date(new Date().getTime()+604800000));
				match.setStartMoment(new Date());
				match.setTitle("Match");
				match.setTournament(tournament);
				List<Team> teams=(List<Team>) tournament.getTeams();
				Collection<Team> teams1=new ArrayList<Team>();
				Team team1=teams.get(i);
				
				Team team2=teams.get(i+1);
				Collection<Match> matchsTeam1=team1.getMatchs();
				Collection<Match> matchsTeam2=team2.getMatchs();
				matchsTeam1.add(match);
				matchsTeam2.add(match);
				team1.setMatchs(matchsTeam1);
				team2.setMatchs(matchsTeam2);
				teams1.add(team1);
				
				teams1.add(team2);
				match.setTeams(teams1);
				match.setDescription(".");
				match.setPlayed(false);
				matchs.add(match);
				
			
				
				tournament.setMatches(matchs);
				
				
				tournamentService.save(tournament);
				teamService.save(team1);
				teamService.save(team2);
				matchService.save(match);

				
				
			}
			
			
			
			
			
			
		}else {
			
			Collection<Match> matchs= tournament.getMatches();
			for(int i=0 ; i < (tournament.getTeams().size()-1) ; i+=2){
				
				
				domain.Match match=new Match();
				match.setCreationMoment(new Date());
				match.setDescription(".");
				match.setFinishMoment(new Date(new Date().getTime()+604800000));
				match.setStartMoment(new Date());
				match.setTitle("Match");
				match.setTournament(tournament);
				List<Team> teams=(List<Team>) tournament.getTeams();
				Collection<Team> teams1=new ArrayList<Team>();
				Team team1=teams.get(i);
				
				Team team2=teams.get(i+1);
				Collection<Match> matchsTeam1=team1.getMatchs();
				Collection<Match> matchsTeam2=team2.getMatchs();
				matchsTeam1.add(match);
				matchsTeam2.add(match);
				team1.setMatchs(matchsTeam1);
				team2.setMatchs(matchsTeam2);
				teams1.add(team1);
				
				teams1.add(team2);
				match.setTeams(teams1);
				match.setDescription(".");
				match.setPlayed(false);
				matchs.add(match);
				
				
				
				
				
				tournament.setMatches(matchs);
				tournamentService.save(tournament);
				teamService.save(team1);
				teamService.save(team2);
				matchService.save(match);
				

				
					
			}
			
			
			
			
			
		}
		
		
		return manageTournament(id);
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		
		Authority authority =new Authority();
		authority.setAuthority("CUSTOMER");
		
		Collection<Tournament> tournaments = null;
		if (LoginService.getPrincipal().getAuthorities().contains(authority)){
			tournaments= tournamentService.findAllTournamentsCreatedByCustomerId();
		}else{
			tournaments=tournamentService.findAllTournamentsCreatedByUserId();
		}
		if(tournaments== null)
			new Throwable("no tournaments for this user");
		
		ModelAndView result=new ModelAndView("tournament/user/rounds/list");
		
		result.addObject("tournaments", tournaments);
		
		
		return result;
		
		
	}
	
	@RequestMapping("/manageTournament")
	public ModelAndView manageTournament(@RequestParam int id)
	{
		
		Tournament tournament=tournamentService.findOne(id);
		
		ModelAndView result = new ModelAndView("tournament/user/rounds/manageTournament");
		
		result.addObject("tournament",tournament);
		result.addObject("matches", tournament.getMatches());
		
		/* añadimos los teams que han jugado y los que no */
		boolean AllMatchesPlays= true;
		boolean AllTeamPlays = true;
		
		if(tournament.getMatches().size() == 0){
			result.addObject("AllPlaysC", false);
			result.addObject("AllTeamC", false);
			
			return result;
			}
		
		
			for (Match a : tournament.getMatches()){
				
				if(a.isPlayed() == false ){
					AllMatchesPlays=false;
					break;
				}
			}
			
			for ( Team c : tournament.getTeams()){
				
				if( c.getMatchs().size()==0){
					AllTeamPlays=false;
					break;
				
				}
					
				for (Match d : c.getMatchs()){
					if(tournament.getMatches().contains(d)){
						
					}else{
						AllTeamPlays=false;
						break;
					}
				}
				
				
				
						
					
				
				
			}
			boolean needRounds=true;
		/* comprobamos que si hay empate (numero de equipos pares) */
		
			Collection<Team> winners=new ArrayList<Team>();
			int counter = 0;
			for (Match a : tournament.getMatches()){
				
				winners.add(a.getWinner());
			}
			
			
			for (Team a : tournament.getTeams()){
				
				int result1 = Collections.frequency(winners, a);
				if( result1 > counter){
					counter= result1;
					
				}else if( result1 == counter){
					
					needRounds=false;
				}
				
			}
	
		
		result.addObject("AllPlaysC", AllMatchesPlays);
		result.addObject("AllTeamC", AllTeamPlays);
		result.addObject("needRounds", needRounds);
		
		return result;
		
	}
	
	@RequestMapping("/declareWinnerOfMatch.do")
	public ModelAndView declareWinnerofMatch(@RequestParam int id)
	{
		Match match =matchService.findOne(id);
			
		ModelAndView result = null;
		
		
	    result = new ModelAndView("tournament/user/rounds/declareWinnerOfMatch");
	    result.addObject("teams", match.getTeams());
	    result.addObject("match", match);
		
		return result;
		
		
	}
	
	@RequestMapping("/declareWinnerOfMatchId.do")
	public ModelAndView declareWinnerofMatch(@RequestParam int idTeam, @RequestParam int idMatch)
	{
		Match match = matchService.findOne(idMatch);
		Team team = teamService.findOne(idTeam);
		
		
		Team team2 = null;
		
		for (Team t : match.getTeams()){
			
			if(t.getId() !=  team.getId()){
				team2 = t;
			}
		}
		
		
		match.setWinner(team);
		match.setDefeat(team2);
		match.setPlayed(true);
		
		Collection<Match> winners = team.getWinners();
		winners.add(match);
		team.setWinners(winners);
		
		Collection<Match> defeats = team2.getDefeats();
		defeats.add(match);
		team2.setDefeats(defeats);
		
		
		
		matchService.save(match);
		teamService.save(team);
		teamService.save(team2);
		
		
		
		
		return list();
		
		
	}
	
	@RequestMapping("/secondRounds")
	public ModelAndView secondRounds (@RequestParam int idTournament){
		
		Tournament tournament=tournamentService.findOne(idTournament);
		/* obtenemos los ganadores actuales */
		
		List<Team> ganadores =new ArrayList<Team>();
		
		for (Match c : tournament.getMatches()){
			
			ganadores.add(c.getWinner());
			
			
		}
		
		List<Team> sinjugar =new ArrayList<Team>();
		
		for ( Team c : tournament.getTeams()){
			
			if( c.getMatchs().size()==0){
				sinjugar.add(c);
				continue;
				
			
			}
				
			for (Match d : c.getMatchs()){
				if(tournament.getMatches().contains(d)){
					
				}else{
					sinjugar.add(c);
					
				}
			}
		
		}
		
		ganadores.addAll(sinjugar);
		
		
		
		
		if((ganadores.size() % 2) == 0){
			
			Collection<Match> matchs= tournament.getMatches();
			for(int i=0 ; i < ganadores.size() ; i+=2){
			
				System.out.println(tournament.getTitle());
				
				domain.Match match=new Match();
				match.setCreationMoment(new Date());
				match.setDescription(".");
				match.setFinishMoment(new Date(new Date().getTime()+604800000));
				match.setStartMoment(new Date());
				match.setTitle("Match");
				match.setTournament(tournament);
				
				Collection<Team> teams1=new ArrayList<Team>();
				Team team1=ganadores.get(i);
				
				Team team2=ganadores.get(i+1);
				Collection<Match> matchsTeam1=team1.getMatchs();
				Collection<Match> matchsTeam2=team2.getMatchs();
				matchsTeam1.add(match);
				matchsTeam2.add(match);
				team1.setMatchs(matchsTeam1);
				team2.setMatchs(matchsTeam2);
				teams1.add(team1);
				
				teams1.add(team2);
				match.setTeams(teams1);
				match.setDescription(".");
				match.setPlayed(false);
				matchs.add(match);
				
			
				
				tournament.setMatches(matchs);
				
				
				tournamentService.save(tournament);
				teamService.save(team1);
				teamService.save(team2);
				matchService.save(match);

				
				
			}
			
			
			
			
			
			
		}else {
			
			Collection<Match> matchs= tournament.getMatches();
			for(int i=0 ; i < (ganadores.size()-1) ; i+=2){
				
				
				domain.Match match=new Match();
				match.setCreationMoment(new Date());
				match.setDescription(".");
				match.setFinishMoment(new Date(new Date().getTime()+604800000));
				match.setStartMoment(new Date());
				match.setTitle("Match");
				match.setTournament(tournament);
				
				Collection<Team> teams1=new ArrayList<Team>();
				Team team1=ganadores.get(i);
				
				Team team2=ganadores.get(i+1);
				Collection<Match> matchsTeam1=team1.getMatchs();
				Collection<Match> matchsTeam2=team2.getMatchs();
				matchsTeam1.add(match);
				matchsTeam2.add(match);
				team1.setMatchs(matchsTeam1);
				team2.setMatchs(matchsTeam2);
				teams1.add(team1);
				
				teams1.add(team2);
				match.setTeams(teams1);
				match.setDescription(".");
				match.setPlayed(false);
				matchs.add(match);
				
				
				
				
				
				tournament.setMatches(matchs);
				tournamentService.save(tournament);
				teamService.save(team1);
				teamService.save(team2);
				matchService.save(match);
				

				
					
			}
			
			
			
			
			
		}
		
		
		
		return list();
		
		
		
		
		
		
		
	}
	
	
	
	@RequestMapping("/declareWinnerOfTournament")
	public ModelAndView declareWinnerOfTournament(@RequestParam int idTournament)
	{
		
		Tournament tournament =tournamentService.findOne(idTournament);
		
		if ( tournament == null)
			new Throwable("Bad tournament or bad id");
		
		int numberOfMatchWinned=0;
		int counter=0;
		Team winner = null;
		Collection<Team> winners=new ArrayList<Team>();
		
		for (Match a : tournament.getMatches()){
			
			winners.add(a.getWinner());
		}
		
		
		for (Team a : tournament.getTeams()){
			
			int result = Collections.frequency(winners, a);
			if( result>= counter){
				counter= result;
				winner= a;
			}
			
		}
		
		tournament.setWinner(winner);
		tournamentService.save(tournament);
		
		return list();
		
		
	}

	
	
	
	
}
	




