
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.TournamentRepository;
import domain.Tournament;
@Service
@Transactional
public class TournamentService {
@Autowired
	private TournamentRepository tournamentRepository;
public Collection<Tournament>  findAll(){
return tournamentRepository.findAll();
}
public Tournament findOne(Integer valueOf) {
return tournamentRepository.findOne(valueOf);
}
public Tournament save(Tournament tournament){
return tournamentRepository.save(tournament);
}
}