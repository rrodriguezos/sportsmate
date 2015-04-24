package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Friendship;
import domain.RequestTeam;
@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
	
	
	@Query("select u.friendships from User u where u.id=?1")
	Collection<Friendship> findAllFriendshisByUserId(int userId);
	
	@Query("select f from Friendship f where f.request = false and f.userFriend.id =?1")
	Collection<Friendship> findAllRequestFriendship(int userId);
}
