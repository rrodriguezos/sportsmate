package repositories;

import java.util.Collection;

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
	
	@Query("select u.eventsCreated from User u where u.id=?1")
	Collection<Event> findAllEventsByUserId(int userId);
	
	@Query("select c.events from Customer c where c.id=?1")
	Collection<Event> findAllEventsByCustomerId(int customerId);

	@Query(" select c from Event c where c.startMoment > CURRENT_TIMESTAMP  and c.customer.id=?1 ORDER BY startMoment ASC")
	Collection<Event> finAllEventsCalendar(int id);

	
}
