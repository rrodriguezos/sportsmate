package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query("select e from Event e where e.owner.id=?1")
	Event findUserToEditByUserId(int userId);
	
	@Query("select e from Event e where e.customer.id=?1")
	Event findCustomerToEditByCustomerId(int cusotmerId);
}
