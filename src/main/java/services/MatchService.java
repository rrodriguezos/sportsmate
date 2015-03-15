
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.MatchRepository;
import domain.Match;
@Service
@Transactional
public class MatchService {
@Autowired
	private MatchRepository matchRepository;
public Collection<Match>  findAll(){
return matchRepository.findAll();
}
public Match findOne(Integer valueOf) {
return matchRepository.findOne(valueOf);
}
public Match save(Match match){
return matchRepository.save(match);
}
}