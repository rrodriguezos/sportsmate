
package services;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;
import forms.UserForm;
@Service
@Transactional
public class UserService {
@Autowired
private UserRepository userRepository;

public Collection<User>  findAll()
{
	return userRepository.findAll();
}
public User findOne(Integer valueOf) 
{
		return userRepository.findOne(valueOf);
}
public User save(User user)
{
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

public 	User reconstruct(UserForm form)
{
	User result=new User();
	result.setName(form.getName());
	result.setSurname(form.getSurname());
	result.setEmail(form.getEmail());
	
	 UserAccount userAccount = new UserAccount();
     userAccount.setUsername(form.getUsername());

    String password = form.getPassword();

    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    String md5 = encoder.encodePassword(password, null);
    
    userAccount.setPassword(md5);
    Authority authority = new Authority();
    authority.setAuthority("USER");
    Collection<Authority> authorities = new ArrayList<Authority>();
    authorities.add(authority);
    userAccount.setAuthorities(authorities);
    result.setUserAccount(userAccount);
    
    return result;
}

	//Other business methods ------------------------------------------------
	public User findByPrincipal()
	{
		
		User user;
		UserAccount userAccount;
		int userAccountId;
		
		userAccount = LoginService.getPrincipal();
		userAccountId= userAccount.getId();		
		user = userRepository.findUserByUserAccountId(userAccountId);
		
		return user;
		
	}
	
public boolean userRegistered(String username) 
{
	Boolean res = true;
		if (userRepository.isRegistered(username) == null) {
			res = false;
		}
		return res;
	}

}

