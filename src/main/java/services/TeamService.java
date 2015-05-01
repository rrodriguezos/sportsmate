package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TeamRepository;
import domain.Match;
import domain.RequestTeam;
import domain.Team;
import domain.Tournament;
import domain.User;
import forms.TeamForm;

import forms.TournamentForm;
@Service
@Transactional
public class TeamService {
	
	//Managed repository -----------------------------------------------------
	@Autowired
	private TeamRepository teamRepository;
	
	// Supporting services ---------------------------------------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestTeamService requestTeamService;
	
	@Autowired
	private TournamentService tournamentService;
	
	// Constructors-----------------------------------------------------------
	public TeamService(){
		super();
	}
	
	// Simple CRUD methods ---------------------------------------------------
	
	public Team create()
	{
		Team team;
		Collection<User> users;
		Collection<Tournament> tournaments;
		Collection<Match> matchs;
		Collection<Match> winners;
		Collection<RequestTeam> requestTeams;
		User captain;
		
		
		team = new Team();
		captain = userService.findByPrincipal();
		users = new ArrayList<User>();
		tournaments = new ArrayList<Tournament>();
		matchs = new ArrayList<Match>();
		winners = new ArrayList<Match>();
		requestTeams = new ArrayList<RequestTeam>();
		
		users.add(captain);
		
		team.setCaptain(captain);
		team.setUsers(users);
		team.setTournaments(tournaments);
		team.setMatchs(matchs);
		team.setWinners(winners);
		team.setRequests(requestTeams);
		
		return team;
	}
	
	public Team save(Team team)
	{
		Team result;
		User principal;
		
		principal = userService.findByPrincipal();
		
		result = teamRepository.save(team);
		
		
		if(team.getId() == 0){
			principal.getTeamsCreated().add(result);
			principal.getTeams().add(result);
		}
		
		userService.save(principal);
		
		return result;
	}
	
	public Team saveJoin(Team team)
	{
		
		team = teamRepository.save(team);
		
		return team;
		
	}
	
	public void delete(Team team)
	{
		
		User captain;
		
		captain = team.getCaptain();
		
		for(Tournament t: team.getTournaments()){
		Assert.isTrue(t.getFinishMoment().before(new Date()));
		}
		
		captain.getTeams().remove(team);
		captain.getTeamsCreated().remove(team);
		
		Assert.notNull(team);
		checkPrincipal(team);
		teamRepository.delete(team);
		
		userService.save(captain);
	}
	
	
	//Other business methods ------------------------------------------------
	
	public void checkPrincipal(Team team)
	{
		Assert.isTrue(team.getCaptain().equals(userService.findByPrincipal()));
	}

	public Collection<Team> findAll() 
	{
		return teamRepository.findAll();
	}
	
	public Collection<Team> findAllOtherUser(int userId) 
	{
		return teamRepository.findAllOtherUser(userId);
	}

	public Team findOne(Integer valueOf) 
	{
		return teamRepository.findOne(valueOf);
	}

	public Collection<Team> findAllTeamsByUserId() 
	{
		Collection<Team> all;
		User user;
		int userId;

		user = userService.findByPrincipal();
		userId = user.getId();
		all = teamRepository.findAllTeamsByUserId(userId);

		return all;
	}
	
	public TeamForm construct(Team team)
	{
		TeamForm result;
		
		result = new TeamForm();
		
		result.setId(team.getId());
		result.setMaxNumber(team.getMaxNumber());
		result.setName(team.getName());
		result.setCaptain(team.getCaptain());
		
		return result;
	}
	
	public Team reconstruct(TeamForm teamForm)
	{
		Team result;
		
		if(teamForm.getId() != 0){
			result = findOne(teamForm.getId());
			
			checkPrincipal(result);
		}else{
			result = this.create();
		}
		
		result.setId(teamForm.getId());
		result.setMaxNumber(teamForm.getMaxNumber());
		result.setName(teamForm.getName());
		result.setCaptain(teamForm.getCaptain());
		
		return result;
	}
	
	public void joinTeam(Team team, RequestTeam requestTeam)
	{
		
		User user;
		
		user = requestTeam.getUser();

		requestTeam.setRequest(true);
		team.getUsers().add(user);
		user.getTeams().add(team);
		
		requestTeamService.save(requestTeam);
		save(team);
		userService.save(user);
		
	}
	
	public void DisjoinTeam(Team team)
	{
		
		User user;
		
		user = userService.findByPrincipal();
		
		team.getUsers().remove(user);
		user.getTeams().remove(team);
		
		save(team);
		userService.save(user);
		
	}
	
	public Collection<Team> findAllTeamsByTournament
	(
			TournamentForm tournamentForm) {
		Collection<Team> all;

		all = tournamentForm.getTeams();

		return all;
	}

	public Collection<Team> findAllTeamsByTournamentId(int tournamentId) 
	{
		Collection<Team> all;

		all = teamRepository.findAllTeamsByTournamentId(tournamentId);

		return all;
	}
	
	public Collection<Team> findAllTeamsUserCaptain(int userId)
	{
		
		Collection<Team> result;
		
		result = teamRepository.findAllTeamsUserCaptain(userId);
		
		return result;
		
	}



}

