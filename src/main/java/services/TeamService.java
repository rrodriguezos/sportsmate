
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.TeamRepository;
import domain.Team;
@Service
@Transactional
public class TeamService {
@Autowired
	private TeamRepository teamRepository;
public Collection<Team>  findAll(){
return teamRepository.findAll();
}
public Team findOne(Integer valueOf) {
return teamRepository.findOne(valueOf);
}
public Team save(Team team){
return teamRepository.save(team);
}
}