package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestTeamRepository;
import domain.RequestTeam;
import domain.Team;
import domain.User;

@Service
@Transactional
public class RequestTeamService {
	
	//Managed repository -----------------------------------------------------
	@Autowired
	private RequestTeamRepository requestTeamRepository;
	
	//Supported services -----------------------------------------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	// Constructors-----------------------------------------------------------
	public RequestTeamService() 
	{

		super();

	}
	
	// Simple CRUD methods ---------------------------------------------------
	
	public RequestTeam create(Team team)
	{
		RequestTeam requestTeam;
		Date date;
		User principal;
		
		requestTeam = new RequestTeam();
		date = new Date();
		principal = userService.findByPrincipal();
		requestTeam.setTeam(team);
		requestTeam.setUser(principal);
		requestTeam.setRequestDate(date);
		requestTeam.setRequest(false);
		
		return requestTeam;
	}
	
	public RequestTeam save(RequestTeam requestTeam)
	{
		RequestTeam result;
		
		result = requestTeamRepository.save(requestTeam);
		
		return result;
	}
	
	//Other business methods ------------------------------------------------
	
	public RequestTeam findOne(int id)
	{
		RequestTeam result;
		result = requestTeamRepository.findOne(id);
		return result;
	}
	
	public Collection<RequestTeam> findAllRequestTeamUser(int userId)
	{
		Collection<RequestTeam> result;
		result = requestTeamRepository.findAllRequestTeam(userId);
		return result;
	}
	
	public Collection<RequestTeam> findAllRequestSendFromUser(int userId)
	{
		Collection<RequestTeam> result;
		result = requestTeamRepository.findAllRequestSendFromUser(userId);
		return result;
	}
	
	
	
	public void sendRequest(int teamId)
	{
		User principal;
		Team team;
		RequestTeam request;
		
		team = teamService.findOne(teamId);
		request =create(team);
		principal = userService.findByPrincipal();
		
		
		
		request = save(request);
		
		team.getRequests().add(request);
		principal.getRequests().add(request);
		
		teamService.save(team);
		principal = userService.save(principal);
		
	}
}
