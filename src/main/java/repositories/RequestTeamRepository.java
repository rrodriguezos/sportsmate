package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;
import domain.RequestTeam;

@Repository
public interface RequestTeamRepository extends JpaRepository<RequestTeam, Integer> {
	
	@Query("select r from RequestTeam r where r.request = false and r.team.captain.id =?1")
	Collection<RequestTeam> findAllRequestTeam(int userId);
	
}
