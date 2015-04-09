
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MatchRepository;
import domain.Match;
import domain.Tournament;
import forms.TournamentForm;
@Service
@Transactional
public class MatchService {
@Autowired
	private MatchRepository matchRepository;
@Autowired
private UserService userService;

public Collection<Match>  findAll(){
return matchRepository.findAll();
}
public Match findOne(Integer valueOf) {
return matchRepository.findOne(valueOf);
}
public Match save(Match match){
return matchRepository.save(match);
}

public Collection<Match> findAllMatchesByTournament(TournamentForm tournamentForm)
{
	
	Collection<Match> all;

	all = tournamentForm.getMatches();	
	
	return all;
	
}
public Collection<Match> findAllMatchesByTournament(Tournament tournament) {
	
	Collection<Match> all;
	int tournamentId;
	tournamentId = tournament.getId();
	
	
	all = matchRepository.findAllMatchesByTournamentId(tournamentId);
	
	return all;
}
}