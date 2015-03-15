
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.FriendshipRepository;
import domain.Friendship;
@Service
@Transactional
public class FriendshipService {
@Autowired
	private FriendshipRepository friendshipRepository;
public Collection<Friendship>  findAll(){
return friendshipRepository.findAll();
}
public Friendship findOne(Integer valueOf) {
return friendshipRepository.findOne(valueOf);
}
public Friendship save(Friendship friendship){
return friendshipRepository.save(friendship);
}
}