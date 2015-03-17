
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;
import security.Authority;
import security.UserAccount;
import domain.User;
@Service
@Transactional
public class UserService {
@Autowired
	private UserRepository userRepository;
public Collection<User>  findAll(){
return userRepository.findAll();
}
public User findOne(Integer valueOf) {
return userRepository.findOne(valueOf);
}
public User save(User user){
return userRepository.save(user);
}
public User create()
{
	User user= new User();
	
	UserAccount useraccount = new UserAccount();
	Authority authority = new Authority();
	authority.setAuthority("USER");
	useraccount.addAuthority(authority);
	user.setUserAccount(useraccount);
	
return user;
}
}