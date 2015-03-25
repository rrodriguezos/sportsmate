
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TeamRepository;
import domain.Team;
import domain.User;
@Service
@Transactional
public class TeamService {
@Autowired
	private TeamRepository teamRepository;
@Autowired
	private UserService userService;
public Collection<Team>  findAll(){
return teamRepository.findAll();
}
public Team findOne(Integer valueOf) {
return teamRepository.findOne(valueOf);
}
public Team save(Team team){
return teamRepository.save(team);
}
public Collection<Team> findAllTeamsByUserId() {
	Collection<Team> all;
	User user;
	int userId;
	
	user = userService.findByPrincipal();
	userId = user.getId();
	all = teamRepository.findAllTeamsByUserId(userId);
	
	return all;
}
}