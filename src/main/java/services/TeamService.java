package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TeamRepository;
import domain.Match;
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
		User captain;
		
		
		team = new Team();
		captain = userService.findByPrincipal();
		users = new ArrayList<User>();
		tournaments = new ArrayList<Tournament>();
		matchs = new ArrayList<Match>();
		winners = new ArrayList<Match>();
		
		users.add(captain);
		
		team.setCaptain(captain);
		team.setUsers(users);
		team.setTournaments(tournaments);
		team.setMatchs(matchs);
		team.setWinners(winners);
		
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
	
	public void delete(Team team)
	{
		Assert.notNull(team);
		checkPrincipal(team);
		teamRepository.delete(team);
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
	
	public void joinTeam(Team team)
	{
		
		User user;
		
		user = userService.findByPrincipal();
		
		team.getUsers().add(user);
		user.getTeams().add(team);
		
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
	
public Collection<Team> findAllTeamsByTournament(TournamentForm tournamentForm) {
	Collection<Team> all;

	all = tournamentForm.getTeams();	
	
	return all;
}
public Collection<Team> findAllTeamsByTournamentId(int tournamentId) {
	Collection<Team> all;
	
	all = teamRepository.findAllTeamsByTournamentId(tournamentId);
	
	return all;
}

}