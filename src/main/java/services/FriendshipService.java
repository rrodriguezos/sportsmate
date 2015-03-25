
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FriendshipRepository;
import domain.Friendship;
import domain.User;
@Service
@Transactional
public class FriendshipService {
@Autowired
	private FriendshipRepository friendshipRepository;
@Autowired
	private UserService userService;
public Collection<Friendship>  findAll(){
return friendshipRepository.findAll();
}
public Friendship findOne(Integer valueOf) {
return friendshipRepository.findOne(valueOf);
}
public Friendship save(Friendship friendship){
return friendshipRepository.save(friendship);
}
public Collection<Friendship> findAllFriendshipsByUserId() 
{
	Collection<Friendship> all;
	User user;
	int userId;
	
	user = userService.findByPrincipal();
	userId = user.getId();
	all = friendshipRepository.findAllFriendshisByUserId(userId);
	
	return all;
}
}
