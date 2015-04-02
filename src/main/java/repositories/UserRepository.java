package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.userAccount.id =?1")
	User findUserByUserAccountId(int userAccountId);
	
	@Query("Select u from User u where u.userAccount.username=?1")
	User isRegistered(String username);
	
	@Query("select e.users from Event e where e.id=?1")
	Collection<User> findAllUsersByEventId(int eventId);
}
